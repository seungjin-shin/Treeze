/*FreeMind - A Program for creating and viewing Mindmaps
 *Copyright (C) 2000-2001  Joerg Mueller <joergmueller@bigfoot.com>
 *See COPYING for Details
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/*$Id: Controller.java,v 1.40.14.21.2.64 2010/02/22 21:18:53 christianfoltin Exp $*/

package freemind.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.itextpdf.text.pdf.PdfReader;

import freemind.common.BooleanProperty;
import freemind.controller.MapModuleManager.MapModuleChangeObserver;
import freemind.controller.filter.FilterController;
import freemind.controller.printpreview.PreviewDialog;
import freemind.main.FreeMind;
import freemind.main.FreeMindCommon;
import freemind.main.FreeMindMain;
import freemind.main.LoggedInFrame;
import freemind.main.Resources;
import freemind.main.Tools;
import freemind.modes.MindIcon;
import freemind.modes.MindMap;
import freemind.modes.MindMapNode;
import freemind.modes.Mode;
import freemind.modes.ModeController;
import freemind.modes.ModesCreator;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;
import freemind.modes.attributes.AttributeRegistry;
import freemind.modes.attributes.AttributeTableLayoutModel;
import freemind.modes.browsemode.BrowseMode;
import freemind.modes.mindmapmode.MindMapController;
import freemind.modes.mindmapmode.attributeactors.AttributeManagerDialog;
import freemind.preferences.FreemindPropertyListener;
import freemind.preferences.layout.OptionPanel;
import freemind.preferences.layout.OptionPanel.OptionPanelFeedback;
import freemind.view.MapModule;
import freemind.view.mindmapview.MapView;

/**
 * Provides the methods to edit/change a Node.
 * Forwards all messages to MapModel(editing) or MapView(navigation).
 */
public class Controller  implements MapModuleChangeObserver {

	private HashSet mMapTitleChangeListenerSet = new HashSet();
    /**
     * Converts from a local link to the real file URL of the
     * documentation map. (Used to change this behaviour under MacOSX).
     */
    private static Logger logger;
    /** Used for MAC!!! */
    public  static LocalLinkConverter localDocumentationLinkConverter;
    private static JColorChooser colorChooser = new JColorChooser();
	private LastOpenedList lastOpened;//A list of the pathnames of all the maps that were opened in the last time
    private MapModuleManager mapModuleManager;// new MapModuleManager();
    /**  The current mode */
    private Mode mMode;
    private FreeMindMain frame;
    private JToolBar toolbar;
    private JToolBar filterToolbar;
    private JPanel northToolbarPanel;
    private NodeMouseMotionListener nodeMouseMotionListener;
    private NodeMotionListener nodeMotionListener;
    private NodeKeyListener nodeKeyListener;
    private NodeDragListener nodeDragListener;
    private NodeDropListener nodeDropListener;
    private MapMouseMotionListener mapMouseMotionListener;
    private MapMouseWheelListener mapMouseWheelListener;
    private ModesCreator mModescreator = new ModesCreator(this);
    private PageFormat pageFormat = null;
    private PrinterJob printerJob = null;
    private Icon bswatch = new BackgroundSwatch();//needed for BackgroundAction
    private boolean antialiasEdges = false;
    private boolean antialiasAll = false;
    private Map fontMap = new HashMap();

    private FilterController mFilterController;

    boolean isPrintingAllowed=true;
    boolean menubarVisible=true;
    boolean toolbarVisible=true;
    boolean leftToolbarVisible=true;

    public CloseAction close;
    public Action print;
    public Action printDirect;
    public Action printPreview;
    public Action page;
    public Action quit;

    public Action showAllAttributes = new ShowAllAttributesAction();
    public Action showSelectedAttributes = new ShowSelectedAttributesAction();
    public Action hideAllAttributes = new HideAllAttributesAction();

    public OptionAntialiasAction optionAntialiasAction;
    public Action optionHTMLExportFoldingAction;
    public Action optionSelectionMechanismAction;

    public Action about;
    public Action faq;
    public Action keyDocumentation;
    public Action webDocu;
    public Action documentation;
    public Action license;
    public Action navigationPreviousMap;
    public Action showFilterToolbarAction;
    public Action showAttributeManagerAction;
    public Action navigationNextMap;

    public Action moveToRoot;
    public Action toggleMenubar;
    public Action toggleToolbar;
    public Action toggleLeftToolbar;

    public Action zoomIn;
    public Action zoomOut;
    //dewlit
    public ArrayList<SlideData> slideList = new ArrayList<SlideData>();
    FreemindManager fManager;
    public SlideShow slideShow;
    public Action closeLecture;
    public Action slideShowAction;
    public Action setSlideSequenceIconAction;
    public Action uploadLectureAction;
    private MindMapController mc;
    public NodeAdapter cur;
    public NodeAdapter prev;
    public NodeAdapter next;
    public NodeAdapter focus;
    public AddQuestionNode addQNode;
	public CheckNodeType chkNodeType;
    
    

	public MindMapController getMc() {
		return mc;
	}
	public NodeAdapter getFocus() {
		return focus;
	}
	public void setFocus(NodeAdapter focus) {
		this.focus = focus;
	}

	public ArrayList<OutputStream> naviOs = new ArrayList<OutputStream>();
	public ArrayList<Socket> socketList = new ArrayList<Socket>();
    public ArrayList<Socket> getSocketList() {
		return socketList;
	}

	int yesCnt = 0;
    int noCnt = 0;
    int totalCnt = 0;
    int classId = 0;
    
    public void setMc(MindMapController mc) {
    	this.mc = mc;
    }
    public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getYesCnt() {
		return yesCnt;
	}

	public void setYesCnt(int yesCnt) {
		this.yesCnt = yesCnt;
	}

	public int getNoCnt() {
		return noCnt;
	}

	public void setNoCnt(int noCnt) {
		this.noCnt = noCnt;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public FreemindSocket fmSck;// = new FreemindSocket(this, getMc());
    
	public void startFreemindSocket(){
		fmSck = new FreemindSocket(this, getMc(), fManager.getIn());
		fmSck.start();
		System.out.println("Controller startFreemindSocket");
	}
	
	public ArrayList<OutputStream> getNaviOs() {
		return naviOs;
	}

	public SlideShow getSlideShow() {
    	return slideShow;
    }
    
    public void setSlideShow(SlideShow slideShow) {
    	this.slideShow = slideShow;
    }
    
    public ArrayList<SlideData> getSlideList() {
    	return slideList;
    }
    //dewlit
    
    public void makeUploadXml(){
    	File downFold = new File(fManager.getDownPath());
    	if(!downFold.exists())
    		downFold.mkdir();
    	
    	File mmFile = new File(fManager.getDownPath(), "tmp.mm");
		File forUpmmFile = new File(fManager.getDownPath(), "upload.mm");
		OutputStreamWriter out = null;
		OutputStreamWriter forUploadXmlOw = null;
		String rdStr;
		String nodeText;
		String nodeImg;
		String preStr = null;
		int start, end;
		String filePath = fManager.getFilePath();
		
		if(System.getProperty("file.separator").equals("\\"))
			filePath = filePath.substring(2, filePath.length());
		//in freemind, can't read such as "C:" windows file separator
		
		try {
			out = new OutputStreamWriter(new FileOutputStream(mmFile), "UTF-8");
			forUploadXmlOw = new OutputStreamWriter(new FileOutputStream(forUpmmFile), "UTF-8");
			
			FreemindManager.getInstance().getmModel().getXml(out, true, getMc().getRootNode());
			
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(mmFile),"UTF-8"));
			
			for(int i = 0; i < 2; i++){
				preStr = in.readLine(); // 둘째 줄까지 읽어
				forUploadXmlOw.write(preStr + "\n");
			}
			
			preStr = in.readLine(); // 루트 
			preStr = preStr.substring(0, preStr.length() - 1); // 끝에 > 없애기
			preStr += " NODETYPESTR=\"Slide\">";
			forUploadXmlOw.write(preStr + "\n"); 
			
			while((rdStr = in.readLine()) != null){
				
				if(rdStr.indexOf("richcontent") >= 0){
					start = rdStr.indexOf("<p>");
					end = rdStr.indexOf("</p>");
					nodeText = rdStr.substring(start + 3, end); // <p> 길이 더하
					
					start = rdStr.indexOf(filePath);
					end = rdStr.indexOf(".jpg\"");
					nodeImg = rdStr.substring(start + filePath.length() + 1, end); // 마지막 폴더 구분자 / or \ 때문에 + 1
					
					preStr = preStr.substring(0, preStr.length() - 1); // 끝에 > 없애기 
					
					preStr += " NODETYPESTR=\"Slide\" TEXT=\"" + nodeText + "\" IMGPATH=\"" + nodeImg + "\">";
					
					forUploadXmlOw.write(preStr + "\n");
				}
				else{
					preStr = rdStr;
					
					if(preStr.indexOf("<node") == -1)
						forUploadXmlOw.write(preStr + "\n");
						
				}
				
			}
			in.close();
			forUploadXmlOw.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    
	public void recurSetUploadXmlID(NodeAdapter node) {
		NodeAdapter forSetID = node;
		int i;
		int cnt;

		forSetID.setNodeID(System.nanoTime() + "");
		
		cnt = forSetID.getChildCount();

		for (i = 0; i < cnt; i++) {
			recurSetUploadXmlID((NodeAdapter) forSetID.getChildAt(i));
		}
	}
    
    public void removeAllIcon(NodeAdapter node){
		NodeAdapter forRemoveIcon = node;
		int i;
		// Question 노드 추가 하기 전 카운트
		int cnt = forRemoveIcon.getChildCount();

		if(forRemoveIcon.getIcons().size() != 0){
			forRemoveIcon.removeIcon(0);
			getModeController().nodeChanged(forRemoveIcon);
		}
		
		for (i = 0; i < cnt; i++) {
			removeAllIcon((NodeAdapter) forRemoveIcon.getChildAt(i));
		}
    }
    
    public void setSequenceIcon(){
    	NodeAdapter tmp;
    	NodeAdapter root = (NodeAdapter) mc.getRootNode();
    	
    	for(int i = 0; i < root.getChildCount(); i++){
    		if(i > 8) // 아이콘 9 까지만 추가
    			break;
    		
    		tmp = (NodeAdapter) root.getChildAt(i);
    		
    		MindIcon icon = MindIcon.factory("full-" + (i + 1));
    		tmp.addIcon(icon, -1); 
    		
			getModeController().nodeChanged(tmp); 
    	}
    	
    }
    
    

	public Action showSelectionAsRectangle;
    public PropertyAction propertyAction;
	public OpenURLAction freemindUrl;

	// this values better suit at least the test purposes
    private static final String[] zooms = {"25%","50%","75%","100%","150%","200%","300%","400%"};
//    private static final String[] zooms = {"25%","40%","60%","75%","100%","125%","150%","200%"};

    //
    // Constructors
    //
    public Controller(FreeMindMain frame) {
        this.frame = frame;
        if(logger == null) {
            logger = frame.getLogger(this.getClass().getName());
        }
        //dewlit
//        Thread t = new tmpSocketThread();
    	//t.start();
    	
        fManager = FreemindManager.getInstance();
        fManager.setC(this);
        
//        fManager.setFilePath("/Users/dewlit/Desktop/TreezeIMG/");
        
        slideShow = fManager.getSlideShow();
        slideShow.setC(this);
        //dewlit
        addQNode = new AddQuestionNode(this);
        chkNodeType = new CheckNodeType(this);
        
//        Thread t = new tmpSocketThread();
//       	t.start();
        //dewlit
        
        /** 
         * Arranges the keyboard focus especially after 
         * opening FreeMind.
         * */
        KeyboardFocusManager focusManager =
            KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener(
            new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    String prop = e.getPropertyName();
                    if ("focusOwner".equals(prop)) {
                        Component comp = (Component)e.getNewValue();
//                        logger.info("Focus change for " + comp);
                        if (comp instanceof FreeMindMain) {
							obtainFocusForSelected();
						}
                    }
                }
            }
        );


        localDocumentationLinkConverter = new DefaultLocalLinkConverter();

        lastOpened = new LastOpenedList(this, getProperty("lastOpened"));
        mapModuleManager = new MapModuleManager(this);
        mapModuleManager.addListener(this);

        nodeMouseMotionListener = new NodeMouseMotionListener(this);
        nodeMotionListener = new NodeMotionListener(this);
        nodeKeyListener = new NodeKeyListener(this);
        nodeDragListener = new NodeDragListener(this);
        nodeDropListener = new NodeDropListener(this);

        mapMouseMotionListener = new MapMouseMotionListener(this);
        mapMouseWheelListener = new MapMouseWheelListener(this);

        close = new CloseAction(this);

        print = new PrintAction(this,true);
        printDirect = new PrintAction(this,false);
        printPreview = new PrintPreviewAction(this);
        page = new PageAction(this);
        quit = new QuitAction(this);
        about = new AboutAction(this);
        freemindUrl = new OpenURLAction(this, getResourceString("FreeMind"), getProperty("webFreeMindLocation"));
        faq = new OpenURLAction(this, getResourceString("FAQ"), getProperty("webFAQLocation"));
        keyDocumentation = new KeyDocumentationAction(this);
        webDocu = new OpenURLAction(this, getResourceString("webDocu"), getProperty("webDocuLocation"));
        documentation = new DocumentationAction(this);
        license = new LicenseAction(this);
        navigationPreviousMap = new NavigationPreviousMapAction(this);
        showFilterToolbarAction = new ShowFilterToolbarAction(this);
        showAttributeManagerAction = new ShowAttributeDialogAction(this);
        navigationNextMap = new NavigationNextMapAction(this);
        toggleMenubar = new ToggleMenubarAction(this);
        toggleToolbar = new ToggleToolbarAction(this);
        toggleLeftToolbar = new ToggleLeftToolbarAction(this);
        optionAntialiasAction = new OptionAntialiasAction(this);
        optionHTMLExportFoldingAction = new OptionHTMLExportFoldingAction(this);
        optionSelectionMechanismAction = new OptionSelectionMechanismAction(this);
        
        zoomIn = new ZoomInAction(this);
        zoomOut = new ZoomOutAction(this);
        propertyAction = new PropertyAction(this);
        showSelectionAsRectangle = new ShowSelectionAsRectangleAction(this);
        
        //dewlit
        closeLecture = new CloseLectureAction();
        slideShowAction = new SlideShowAction();
        setSlideSequenceIconAction = new SetSlideSequenceIconAction();
        uploadLectureAction = new UploadLectureAction();

        moveToRoot = new MoveToRootAction(this);

        //Create the ToolBar
        northToolbarPanel = new JPanel(new BorderLayout());
        toolbar = new MainToolBar(this);
        
        mFilterController = new FilterController(this);
        filterToolbar = mFilterController.getFilterToolbar();
        getFrame().getContentPane().add( northToolbarPanel, BorderLayout.NORTH );
        northToolbarPanel.add( toolbar, BorderLayout.NORTH);
        northToolbarPanel.add( filterToolbar, BorderLayout.SOUTH);

        setAllActions(false);

        if (!Tools.isAvailableFontFamily(getProperty("defaultfont"))) {
           logger.warning("Warning: the font you have set as standard - "+getProperty("defaultfont")+
                              " - is not available.");
           frame.setProperty("defaultfont","SansSerif"); }

    }

    //
    // get/set methods
    //
    public static final String JAVA_VERSION = System.getProperty("java.version");

    public String getProperty(String property) {
	   return frame.getProperty(property); }

	public int getIntProperty(String property, int defaultValue) {
	   return frame.getIntProperty(property, defaultValue); }

    public void setProperty(String property, String value) {
		String oldValue = getProperty(property);
		firePropertyChanged(property, value, oldValue);
		

    }

	private void firePropertyChanged(String property, String value,
			String oldValue) {
		if(oldValue == null || ! oldValue.equals(value))
		{
			frame.setProperty(property, value);
			for (Iterator i = Controller.getPropertyChangeListeners().iterator(); i.hasNext();) {
				FreemindPropertyListener listener = (FreemindPropertyListener) i
				.next();
				listener.propertyChanged(property, value, oldValue);
			}
		}
	}

    public FreeMindMain getFrame() {
        return frame;
    }

    public JFrame getJFrame() {
        FreeMindMain f = getFrame();
        if (f instanceof JFrame) return (JFrame) f;
        return null;
    }

    public URL getResource(String resource) {
        return getFrame().getResource(resource);
    }

    public String getResourceString(String resource) {
          return frame.getResourceString(resource);
    }

	/** @return the current modeController. */
	public ModeController getModeController() {
		if (getMapModule() != null) {
			return getMapModule().getModeController();
		}
		// no map present: we take the default:
		return getMode().getDefaultModeController();
	}



    /**Returns the current model*/
    public MindMap getModel() {
       if (getMapModule() != null) {
          return getMapModule().getModel();
       }
       return null;
    }

    public MapView getView() {
        if (getMapModule() != null) {
            return getMapModule().getView();
        } else {
//           System.err.println("[Freemind-Developer-Internal-Warning (do not write a bug report, please)]: Tried to get view without being able to get map module.");
           return null;
        }
    }

    Set getModes() {
        return mModescreator.getAllModes();
    }

    public Mode getMode() {
        return mMode;
    }

    public String[] getZooms() {
       return zooms; }

    public MapModuleManager getMapModuleManager() {
        return mapModuleManager;
    }

    public LastOpenedList getLastOpenedList() {
        return lastOpened;
    }

    //

    public MapModule getMapModule() {
        return getMapModuleManager().getMapModule();
    }

    private JToolBar getToolBar() {
        return toolbar;
    }

    //

    public Font getFontThroughMap(Font font) {
       if (!fontMap.containsKey(font.toString())) {
          fontMap.put(font.toString(),font); }
       return (Font)fontMap.get(font.toString()); }

    //

    public void setAntialiasEdges(boolean antialiasEdges) {
       this.antialiasEdges = antialiasEdges; }

    public void setAntialiasAll(boolean antialiasAll) {
       this.antialiasAll = antialiasAll; }

    private boolean getAntialiasEdges() {
       return antialiasEdges; }

    private boolean getAntialiasAll() {
       return antialiasAll; }

    public Font getDefaultFont() {
       // Maybe implement handling for cases when the font is not
       // available on this system.

       int fontSize = getDefaultFontSize();
       int fontStyle = getDefaultFontStyle();
       String fontFamily = getDefaultFontFamilyName();

       return getFontThroughMap (new Font(fontFamily, fontStyle, fontSize)); }

	/**
     */
    public String getDefaultFontFamilyName() {
        String fontFamily = getProperty("defaultfont");
        return fontFamily;
    }

    /**
     */
    public int getDefaultFontStyle() {
        int fontStyle = frame.getIntProperty("defaultfontstyle",0);
        return fontStyle;
    }

    /**
     */
    public int getDefaultFontSize() {
        int fontSize = frame.getIntProperty("defaultfontsize",12);
        return fontSize;
    }

    /** Static JColorChooser to have  the recent colors feature. */
	static public JColorChooser getCommonJColorChooser() {
		return colorChooser;
	}

	public static Color showCommonJColorChooserDialog(Component component,
		String title, Color initialColor) throws HeadlessException {

		final JColorChooser pane = getCommonJColorChooser();
		pane.setColor(initialColor);

		ColorTracker ok = new ColorTracker(pane);
		JDialog dialog = JColorChooser.createDialog(component, title, true, pane, ok, null);
		dialog.addWindowListener(new Closer());
		dialog.addComponentListener(new DisposeOnClose());

		dialog.show(); // blocks until user brings dialog down...

		return ok.getColor();
	}


	private static class ColorTracker implements ActionListener, Serializable {
		JColorChooser chooser;
		Color color;

		public ColorTracker(JColorChooser c) {
			chooser = c;
		}

		public void actionPerformed(ActionEvent e) {
			color = chooser.getColor();
		}

		public Color getColor() {
			return color;
		}
	}

	static class Closer extends WindowAdapter implements Serializable{
		 public void windowClosing(WindowEvent e) {
			 Window w = e.getWindow();
			 w.hide();
		 }
	 }

	 static class DisposeOnClose extends ComponentAdapter implements Serializable{
		 public void componentHidden(ComponentEvent e) {
			 Window w = (Window)e.getComponent();
			 w.dispose();
		 }
	 }


	 public boolean isMapModuleChangeAllowed(MapModule oldMapModule, Mode oldMode, MapModule newMapModule, Mode newMode) {
		return true;
	}

	 public void afterMapClose(MapModule pOldMapModule, Mode pOldMode) {
	 }

	 public void beforeMapModuleChange(MapModule oldMapModule, Mode oldMode, MapModule newMapModule, Mode newMode) {
        ModeController oldModeController;
        this.mMode = newMode;
        if (oldMapModule != null) {
            // shut down screens of old view + frame
            oldModeController = oldMapModule.getModeController();
            oldModeController.setVisible(false);
            oldModeController.shutdownController();
        } else {
            if (oldMode != null) {
                oldModeController = oldMode.getDefaultModeController();
            } else {
                return;
            }
        }
        if (oldModeController.getModeToolBar() != null) {
            toolbar.remove(oldModeController.getModeToolBar());
        }
        /* other toolbars are to be removed too. */
        if (oldModeController.getLeftToolBar() != null) {
            getFrame().getContentPane().remove(
                    oldModeController.getLeftToolBar());
        }
    }

	 public void afterMapModuleChange(MapModule oldMapModule, Mode oldMode, MapModule newMapModule, Mode newMode) {
        ModeController newModeController;
        if (newMapModule != null) {
            getFrame().setView(newMapModule.getView());
            setAllActions(true);
            if ((getView().getSelected() == null)) {
            	moveToRoot();
            }
            lastOpened.mapOpened(newMapModule);
            ((MainToolBar) getToolbar()).setZoomComboBox(newMapModule.getView()
                    .getZoom());
            // old
//            obtainFocusForSelected();
            newModeController = newMapModule.getModeController();
            newModeController.startupController();
            newModeController.setVisible(true);
            // old
//            obtainFocusForSelected();
        } else {
            newModeController = newMode.getDefaultModeController();
            getFrame().setView(null);
            setAllActions(false);
        }
        setTitle();
        JToolBar newToolBar = newModeController.getModeToolBar();
        if (newToolBar != null) {
            toolbar.add(newToolBar);
            newToolBar.repaint();
        }
        /* new left toolbar. */
        Component newLeftToolBar = newModeController.getLeftToolBar();
        if (newLeftToolBar != null) {
            getFrame().getContentPane().add(newLeftToolBar, BorderLayout.WEST);
            if(leftToolbarVisible){
                newLeftToolBar.setVisible(true);
                newLeftToolBar.repaint();
            }
            else{
                newLeftToolBar.setVisible(false);
            }
        }
        toolbar.validate();
        toolbar.repaint();
        MenuBar menuBar = getFrame().getFreeMindMenuBar();
        menuBar.updateMenus(newModeController);
        menuBar.revalidate();
        menuBar.repaint();
        // new
        obtainFocusForSelected();
    }

	public void numberOfOpenMapInformation(int number) {
		navigationPreviousMap.setEnabled(number>0);
		navigationNextMap.setEnabled(number>0);
	}


    /** Creates a new mode (controller), activates the toolbars, title and deactivates all
     * actions.
     * Does nothing, if the mode is identical to the current mode.
     *
     * @return false if the change was not successful.
     */
    public boolean createNewMode(String mode) {
        if (getMode() != null && mode.equals(getMode().toString())) {
            return true;
        }

        //Check if the mode is available and create ModeController.
        Mode newMode = mModescreator.getMode(mode);
        if (newMode == null) {
            errorMessage(getResourceString("mode_na")+": "+mode);
            return false;
        }


        // change the map module to get changed toolbars etc.:
        getMapModuleManager().setMapModule(null, newMode);

        setTitle();
        getMode().activate();

        Object[] messageArguments = {
        		getMode().toLocalizedString()
        };
        MessageFormat formatter = new MessageFormat(getResourceString("mode_status"));
        getFrame().out(formatter.format(messageArguments));

        return true;
    }


    public void setMenubarVisible(boolean visible) {
        menubarVisible = visible;
        getFrame().getFreeMindMenuBar().setVisible(menubarVisible);
    }

    public void setToolbarVisible(boolean visible) {
        toolbarVisible = visible;
        toolbar.setVisible(toolbarVisible);
    }

    /**
     * @return Returns the main toolbar.
     */
    public JToolBar getToolbar() {
        return toolbar;
    }
    public void setLeftToolbarVisible(boolean visible) {
        leftToolbarVisible = visible;
        if (getMode() == null){
            return;
        }
        final Component leftToolBar = getModeController().getLeftToolBar();
        if (leftToolBar != null) {
           leftToolBar.setVisible(leftToolbarVisible);
           ((JComponent)leftToolBar.getParent()).revalidate();
        }
    }

    public NodeKeyListener getNodeKeyListener() {
        return nodeKeyListener;
    }

    public NodeMouseMotionListener getNodeMouseMotionListener() {
        return nodeMouseMotionListener;
    }

    public NodeMotionListener getNodeMotionListener() {
        return nodeMotionListener;
    }

    public MapMouseMotionListener getMapMouseMotionListener() {
        return mapMouseMotionListener;
    }

    public MapMouseWheelListener getMapMouseWheelListener() {
        return mapMouseWheelListener;
    }

    public NodeDragListener getNodeDragListener() {
        return nodeDragListener;
    }

    public NodeDropListener getNodeDropListener() {
        return nodeDropListener;
    }

    public void setFrame(FreeMindMain frame) {
        this.frame = frame;
    }

    /**
     * I don't understand how this works now (it's called twice etc.)
     * but it _works_ now. So let it alone or fix it to be understandable,
     * if you have the time ;-)
     */
    void moveToRoot() {
        if (getMapModule() != null) {
            getView().moveToRoot();
        }
    }

    /** Closes the actual map.
     * @param force true= without save.
     */
    public void close(boolean force) {
		getMapModuleManager().close(force);
	}



// (PN) %%%
//    public void select( NodeView node) {
//        getView().select(node,false);
//        getView().setSiblingMaxLevel(node.getModel().getNodeLevel()); // this level is default
//    }
//
//    void selectBranch( NodeView node, boolean extend ) {
//        getView().selectBranch(node,extend);
//    }
//
//    boolean isSelected( NodeView node ) {
//        return getView().isSelected(node);
//    }
//
//    void centerNode() {
//        getView().centerNode(getView().getSelected());
//    }
//
//    private MindMapNode getSelected() {
//        return getView().getSelected().getModel();
//    }

    public void informationMessage(Object message) {
       JOptionPane.showMessageDialog(getFrame().getContentPane(), message.toString(), "FreeMind", JOptionPane.INFORMATION_MESSAGE); }

    public void informationMessage(Object message, JComponent component) {
       JOptionPane.showMessageDialog(component, message.toString(), "FreeMind", JOptionPane.INFORMATION_MESSAGE); }

    public void errorMessage(Object message) {
		String myMessage = "";

		if (message != null) {
			myMessage = message.toString();
		} else {
			myMessage = getResourceString("undefined_error");
			if (myMessage == null) {
				myMessage = "Undefined error";
			}
		}
		JOptionPane.showMessageDialog(getFrame().getContentPane(), myMessage, "FreeMind", JOptionPane.ERROR_MESSAGE);

	}

    public void errorMessage(Object message, JComponent component) {
       JOptionPane.showMessageDialog(component, message.toString(), "FreeMind", JOptionPane.ERROR_MESSAGE); }

    public void obtainFocusForSelected() {
//    	logger.finest("obtainFocusForSelected");
    	if (getView() != null) { // is null if the last map was closed.
    		logger.info("Requesting Focus for " + getView().getSelected());
    		getView().getSelected().requestFocus();
    	} else {
    		// fc, 6.1.2004: bug fix, that open and quit are not working if no map is present.
    		// to avoid this, the menu bar gets the focus, and everything seems to be all right!!
    		// but I cannot avoid thinking of this change to be a bad hack ....
    		logger.info("No view present. No focus!");
    		getFrame().getFreeMindMenuBar().requestFocus();
    	}
    }

    //
    // Map Navigation
    //

    //
    // other
    //

    public void setZoom(float zoom) {
        getView().setZoom(zoom);
        ((MainToolBar)toolbar).setZoomComboBox(zoom);
        // show text in status bar:
        Object[] messageArguments = {
         String.valueOf(zoom*100f)
        };
        String stringResult = Resources.getInstance().format("user_defined_zoom_status_bar", messageArguments);
		getFrame().out(stringResult);
    }


    //////////////
    // Private methods. Internal implementation
    ////////////


    //
    // Node editing
    //
// (PN)
//    private void getFocus() {
//        getView().getSelected().requestFocus();
//    }

    //
    // Multiple Views management
    //


	/**
	 * Set the Frame title with mode and file if exist
	 */
	public void setTitle() {
		Object[] messageArguments = {
			getMode().toLocalizedString()
		};
		MessageFormat formatter = new MessageFormat
		   (getResourceString("mode_title"));
		String title = formatter.format(messageArguments);
		String rawTitle = "";
		MindMap model = null;
		MapModule mapModule = getMapModule();
		if (mapModule != null) {
			model = mapModule.getModel();
            rawTitle = mapModule.toString();
			title = rawTitle + (model.isSaved()?"":"*") + " - " + title +
			  ( model.isReadOnly() ?
				" ("+getResourceString("read_only")+")" : "");
            File file = model.getFile();
            if (file != null) {
                title += " " + file.getAbsolutePath();
            }
		}
		getFrame().setTitle(title);
		for (Iterator iterator = mMapTitleChangeListenerSet.iterator(); iterator.hasNext();) {
			MapModuleManager.MapTitleChangeListener listener = (MapModuleManager.MapTitleChangeListener) iterator.next();
			listener.setMapTitle(rawTitle, mapModule, model);
		}
	}

	public void registerMapTitleChangeListener(MapModuleManager.MapTitleChangeListener pMapTitleChangeListener){
		mMapTitleChangeListenerSet.add(pMapTitleChangeListener);
	}
	public void deregisterMapTitleChangeListener(MapModuleManager.MapTitleChangeListener pMapTitleChangeListener){
		mMapTitleChangeListenerSet.remove(pMapTitleChangeListener);
	}

    //
    // Actions management
    //

    /**
     * Manage the availabilty of all Actions dependend
     * of whether there is a map or not
     */
    public void setAllActions(boolean enabled) {
	    print.setEnabled(enabled && isPrintingAllowed);
	    printDirect.setEnabled(enabled && isPrintingAllowed);
	    printPreview.setEnabled(enabled && isPrintingAllowed);
	    page.setEnabled(enabled && isPrintingAllowed);
        close.setEnabled(enabled);
        moveToRoot.setEnabled(enabled);
        showAllAttributes.setEnabled(enabled);
        showSelectedAttributes.setEnabled(enabled);
        hideAllAttributes.setEnabled(enabled);
        showAttributeManagerAction.setEnabled(enabled);
        ((MainToolBar)getToolBar()).setAllActions(enabled);
        showSelectionAsRectangle.setEnabled(enabled);
    }

    //
    // program/map control
    //

    private void quit() {
        String currentMapRestorable = (getModel()!=null) ? getModel().getRestoreable() : null;
        while (getMapModuleManager().getMapModuleVector().size() > 0) {
				if (getMapModule() != null) {
					boolean closingNotCancelled = getMapModuleManager().close(
							false);
					if (!closingNotCancelled) {
						return;
					}
				} else {
					// map module without view open.
					// FIXME: This seems to be a bad hack. correct me!
					getMapModuleManager().nextMapModule();
				}
		}

        String lastOpenedString=lastOpened.save();
        setProperty("lastOpened",lastOpenedString);
        if (currentMapRestorable != null) {
           getFrame().setProperty(FreeMindCommon.ON_START_IF_NOT_SPECIFIED,currentMapRestorable); }
        // getFrame().setProperty("menubarVisible",menubarVisible ? "true" : "false");
        // ^ Not allowed in application because of problems with not working key shortcuts
        setProperty("toolbarVisible", toolbarVisible ? "true" : "false");
        setProperty("leftToolbarVisible", leftToolbarVisible ? "true" : "false");
        setProperty("antialiasEdges", antialiasEdges ? "true" : "false");
        setProperty("antialiasAll", antialiasAll ? "true" : "false");
        if(! getFrame().isApplet()) {
            final int winState = getFrame().getWinState();
        	if (JFrame.MAXIMIZED_BOTH != (winState & JFrame.MAXIMIZED_BOTH)){
        		setProperty("appwindow_x", String.valueOf(getFrame().getWinX()));
        		setProperty("appwindow_y", String.valueOf(getFrame().getWinY()));
        		setProperty("appwindow_width", String.valueOf(getFrame().getWinWidth()));
        		setProperty("appwindow_height", String.valueOf(getFrame().getWinHeight()));
        	}
        	setProperty("appwindow_state", String.valueOf(winState));
        }
        getFrame().saveProperties();
        //save to properties
        System.exit(0);
    }

    private boolean acquirePrinterJobAndPageFormat() {
       if (printerJob == null) {
          try {
             printerJob = PrinterJob.getPrinterJob(); }
          catch (SecurityException ex) {
             isPrintingAllowed = false;
             return false; }}
       if (pageFormat == null) {
           pageFormat = printerJob.defaultPage();
           if (Tools.safeEquals(getProperty("page_orientation"), "landscape")) {
               pageFormat.setOrientation(PageFormat.LANDSCAPE);
           } else if (Tools.safeEquals(getProperty("page_orientation"), "portrait")) {
               pageFormat.setOrientation(PageFormat.PORTRAIT);
           } else if (Tools.safeEquals(getProperty("page_orientation"), "reverse_landscape")) {
               pageFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
           }
       }
       return true; }

    //////////////
    // Inner Classes
    ////////////

    /**
     * Manages the history of visited maps.
     * Maybe explicitly closed maps should be removed from
     * History too?
     */


    //
    // program/map control
    //
    protected class SetSlideSequenceIconAction extends AbstractAction {
        public SetSlideSequenceIconAction() {
        	super("Set Slide Sequence Icon"); 
        }
        public void actionPerformed(ActionEvent e) {
        	removeAllIcon((NodeAdapter) getMc().getRootNode());
			setSequenceIcon();
           }}
    
    protected class UploadLectureAction extends AbstractAction {
        public UploadLectureAction() {
        	super("Upload Lecture"); 
        }
        public void actionPerformed(ActionEvent e) {
        	recurSetUploadXmlID((NodeAdapter) getMc().getRootNode());
    		makeUploadXml();
			
			UploadToServer uts = new UploadToServer();
			try {
				uts.doFileUpload();
				uts.doXmlUpload();
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           }}
    
    
    protected class SlideShowAction extends AbstractAction {
        public SlideShowAction() {
           super("Slide Show"); }
        public void actionPerformed(ActionEvent e) {
        	startSlideShow();
        }}
    
    public void startSlideShow(){
    	chkNodeType.checkNodeType((NodeAdapter)getMc().getRootNode());
    	System.out.println("Controller : check node type");
    	
    	NodeAdapter root = (NodeAdapter)getMc().getRootNode();
    	NodeAdapter next;// = (NodeAdapter)mc.getRootNode();
    	
    	//set FreemindManager isSlideshow 
    	fManager.setSlideShowInfo(true);
    	
    	//set root
    	root.setPrev(null);
    	if(root.hasChildren()){
    		next = (NodeAdapter)root.getChildAt(0);
    		root.setNext(next);
    		
    		for(int i = 0; i < root.getChildCount(); i++){ // root direct childs set
        		recurSetSlideShowInfo((NodeAdapter)root.getChildAt(i));
        	}
    		System.out.println("Controller : set slideShowInfo");
    	}
    	else{
    		System.out.println("Controller : only root");
    		return;
    	}
    	
    	getSlideShow().setfocus((NodeAdapter)getMc().getRootNode().getChildAt(0));
		getSlideShow().show();
		getSlideShow().sendPosition();
    }
    
    protected class CloseLectureAction extends AbstractAction {
        public CloseLectureAction() {
           super("Close lecture"); }
        public void actionPerformed(ActionEvent e) {
//        	final String CLOSELECTURE = "3";
//        	LectureInfo lectureInfo;
//    		lectureInfo = FreemindLectureManager.getInstance();
//    		
//        	String lectureTitle = lectureInfo.getLectureTitle();
//        	String lectureId = lectureInfo.getLectureId() + "";
//
//        	HttpClient httpClient = new DefaultHttpClient();  
//      	  HttpPost post = new HttpPost("http://" + fManager.SERVERIP + ":8080/treeze/setStateOfLecture");
//      	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
//      	  
//      	  StringBody lectureTitleBody = null;
//      	  StringBody profEmailBody = null;
//      	  StringBody lectureState = null;
//      	  
//		try {
//			lectureTitleBody = new StringBody("tmp", Charset.forName("UTF-8"));
//			profEmailBody = new StringBody("minsuk@hansung.ac.kr", Charset.forName("UTF-8"));
//			lectureState = new StringBody("false", Charset.forName("UTF-8"));
//			StringBody lectureIdBody = new StringBody(lectureId, Charset.forName("UTF-8"));
//			
//			multipart.addPart("lectureName", lectureTitleBody);  
//			multipart.addPart("professorEmail", profEmailBody);
//			multipart.addPart("stateOfLecture", lectureState);
//			multipart.addPart("lectureId", lectureIdBody);
//			
//			post.setEntity(multipart);  
//			HttpResponse response = httpClient.execute(post);  
//			HttpEntity resEntity = response.getEntity();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//         
//      	  System.out.println("set state false");
//      	  getSlideList().clear();
//      	  
//      	OutputStream tmpOs;
//		for(int i = 0; i < getNaviOs().size(); i++){
//			tmpOs = getNaviOs().get(i);
//			try {
//				if(tmpOs != null){
//					tmpOs.write(CLOSELECTURE.getBytes("UTF-8"));
//				}
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
      	  fManager.getProfileFrame().setVisible(true);
      	  fManager.getFreemindMainFrame().setVisible(false);
           }}
    

    private class QuitAction extends AbstractAction {
        QuitAction(Controller controller) {
            super(controller.getResourceString("quit"));
        }
        public void actionPerformed(ActionEvent e) {
            quit();
        }
    }

    /**This closes only the current map*/
    public static class CloseAction extends AbstractAction {
        private final Controller controller;
		CloseAction(Controller controller) {
            Tools.setLabelAndMnemonic(this, controller.getResourceString("close"));
			this.controller = controller;
        }
        public void actionPerformed(ActionEvent e) {
            controller.close(false);
        }
    }

    private class PrintAction extends AbstractAction {
        Controller controller;
        boolean isDlg;
        PrintAction(Controller controller, boolean isDlg) {
            super(isDlg ? controller.getResourceString("print_dialog")
					: controller.getResourceString("print"),
					new ImageIcon(getResource("images/fileprint.png")));
            this.controller = controller;
            setEnabled(false);
            this.isDlg = isDlg;
        }
        public void actionPerformed(ActionEvent e) {
            if (!acquirePrinterJobAndPageFormat()) {
               return; }

            printerJob.setPrintable(getView(),pageFormat);

            if (!isDlg || printerJob.printDialog()) {
                try {
                	frame.setWaitingCursor(true);
                    printerJob.print();
                } catch (Exception ex) {
                    freemind.main.Resources.getInstance().logException(ex);
                } finally {
                	frame.setWaitingCursor(false);
                }
            }
        }
    }

    private class PrintPreviewAction extends AbstractAction {
        Controller controller;
        PrintPreviewAction(Controller controller) {
            super(controller.getResourceString("print_preview"));
            this.controller = controller;
        }
        public void actionPerformed(ActionEvent e) {
            if (!acquirePrinterJobAndPageFormat()) {
               return; }
            PreviewDialog previewDialog = new PreviewDialog(controller.getResourceString("print_preview_title"), getView());
            previewDialog.pack();
            previewDialog.setLocationRelativeTo(JOptionPane.getFrameForComponent(getView()));
            previewDialog.setVisible(true);
       }
    }



    private class PageAction extends AbstractAction {
        Controller controller;
        PageAction(Controller controller) {
            super(controller.getResourceString("page"));
            this.controller = controller;
            setEnabled(false);
        }
        public void actionPerformed(ActionEvent e) {
            if (!acquirePrinterJobAndPageFormat()) {
               return; }

            // Ask about custom printing settings
            final JDialog dialog = new JDialog((JFrame)getFrame(), getResourceString("printing_settings"), /*modal=*/true);
            final JCheckBox fitToPage = new JCheckBox(getResourceString("fit_to_page"), Resources.getInstance().getBoolProperty("fit_to_page"));
            final JLabel userZoomL = new JLabel(getResourceString("user_zoom"));
            final JTextField userZoom = new JTextField(getProperty("user_zoom"),3);
            userZoom.setEditable(!fitToPage.isSelected());
            final JButton okButton = new JButton();
            Tools.setLabelAndMnemonic(okButton, getResourceString("ok"));
            final Tools.IntHolder eventSource = new Tools.IntHolder();
            JPanel panel = new JPanel();

            GridBagLayout gridbag = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();

            eventSource.setValue(0);
            okButton.addActionListener (new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     eventSource.setValue(1);
                     dialog.dispose(); }});
            fitToPage.addItemListener (new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    userZoom.setEditable(e.getStateChange() == ItemEvent.DESELECTED);
                }
            });

            //c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            gridbag.setConstraints(fitToPage, c);
            panel.add(fitToPage);
            c.gridy = 1;
            c.gridwidth = 1;
            gridbag.setConstraints(userZoomL, c);
            panel.add(userZoomL);
            c.gridx = 1;
            c.gridwidth = 1;
            gridbag.setConstraints(userZoom, c);
            panel.add(userZoom);
            c.gridy = 2;
            c.gridx = 0;
            c.gridwidth = 3;
            c.insets = new Insets(10,0,0,0);
            gridbag.setConstraints(okButton, c);
            panel.add(okButton);
            panel.setLayout(gridbag);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setContentPane(panel);
            dialog.setLocationRelativeTo((JFrame)getFrame());
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();  // calculate the size
            dialog.setVisible(true);

            if (eventSource.getValue() == 1) {
               setProperty("user_zoom", userZoom.getText());
               setProperty("fit_to_page", fitToPage.isSelected() ? "true" : "false"); }
            else
               return;

            // Ask user for page format (e.g., portrait/landscape)
            pageFormat = printerJob.pageDialog(pageFormat);
            if (pageFormat.getOrientation() == PageFormat.LANDSCAPE) {
                setProperty("page_orientation", "landscape");
            } else if (pageFormat.getOrientation() == PageFormat.PORTRAIT) {
                setProperty("page_orientation", "portrait");
            } else if (pageFormat.getOrientation() == PageFormat.REVERSE_LANDSCAPE) {
                setProperty("page_orientation", "reverse_landscape");
            }
        }
    }

    public interface LocalLinkConverter {
    		String convertLocalLink(String link);
    }

    private class DefaultLocalLinkConverter implements LocalLinkConverter {

        public String convertLocalLink(String map) {
            /* new handling for relative urls. fc, 29.10.2003.*/
            String applicationPath = frame.getFreemindBaseDir();
            return "file:" + applicationPath + map.substring(1);//remove "." and make url
			/* end: new handling for relative urls. fc, 29.10.2003.*/
		}
    }

    //
    // Help
    //

    private class DocumentationAction extends AbstractAction {
		Controller controller;
        DocumentationAction(Controller controller) {
            super(controller.getResourceString("documentation"));
            this.controller = controller;
        }
        public void actionPerformed(ActionEvent e) {
            String map = controller.getFrame().getResourceString("browsemode_initial_map");
            // if the current language does not provide its own translation, POSTFIX_TRANSLATE_ME is appended:
            map = Tools.removeTranslateComment(map);
            if (map != null && map.startsWith("."))  {
                try{
                    map = localDocumentationLinkConverter.convertLocalLink(map);
                }
                catch(AccessControlException ex){
                    webDocu.actionPerformed(e);
                    return;
                }
            }
            if (map != null && map != "") {
                URL url = null;
                try {
                    url = new URL(map);
                } catch (MalformedURLException e2) {
                    freemind.main.Resources.getInstance().logException(e2);
                    return;
                }
                final URL endUrl = url;
                // invokeLater is necessary, as the mode changing removes all menus (inclusive this action!).
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            createNewMode(BrowseMode.MODENAME);
                            controller.getModeController().load(endUrl);
                        } catch (Exception e1) {
                            freemind.main.Resources.getInstance().logException(e1);
                        }
                    }
                });
            }
        }
    }

    private class KeyDocumentationAction extends AbstractAction {
    	Controller controller;
    	KeyDocumentationAction(Controller controller) {
    		super(controller.getResourceString("KeyDoc"));
    		this.controller = controller;
    	}
    	public void actionPerformed(ActionEvent e) {
    		String urlText = controller.getFrame().getResourceString("pdfKeyDocLocation");
    		// if the current language does not provide its own translation, POSTFIX_TRANSLATE_ME is appended:
    		urlText = Tools.removeTranslateComment(urlText);
    		try{
	    		if (urlText != null && urlText.startsWith("."))  {
	    				urlText = localDocumentationLinkConverter.convertLocalLink(urlText);
	    		}
	    		if (urlText != null && urlText != "") {
	    			URL url = null;
	    				url = new URL(urlText);
	    				controller.getFrame().openDocument(url);
	    		}
    		} catch (Exception e2) {
    			freemind.main.Resources.getInstance().logException(e2);
    			return;
    		}
    	}
    }
    
    private class AboutAction extends AbstractAction {
        Controller controller;
        AboutAction(Controller controller) {
            super(controller.getResourceString("about"));
            this.controller = controller;
        }
        public void actionPerformed(ActionEvent e) {
           JOptionPane.showMessageDialog(getFrame().getViewport(), controller
					.getResourceString("about_text")
					+ getFrame().getFreemindVersion(), controller
					.getResourceString("about"),
					JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class LicenseAction extends AbstractAction {
        Controller controller;
        LicenseAction(Controller controller) {
            super(controller.getResourceString("license"));
            this.controller = controller;
        }
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(getView(), controller
					.getResourceString("license_text"), controller
					.getResourceString("license"),
					JOptionPane.INFORMATION_MESSAGE);
        }
    }


    //
    // Map navigation
    //

    private class NavigationPreviousMapAction extends AbstractAction {
        NavigationPreviousMapAction(Controller controller) {
            super(controller.getResourceString("previous_map"),
                  new ImageIcon(getResource("images/1leftarrow.png")));
            setEnabled(false);
        }
        public void actionPerformed(ActionEvent event) {
            mapModuleManager.previousMapModule();
        }
    }

    private class ShowAttributeDialogAction extends AbstractAction {
        private Controller c;
        ShowAttributeDialogAction(Controller c) {
            super(c.getResourceString("attributes_dialog"),
                  new ImageIcon(getResource("images/showAttributes.gif")));
            this.c = c;
        }
		private AttributeManagerDialog getAttributeDialog() {
			if (attributeDialog == null) {
			    attributeDialog = new AttributeManagerDialog(c);
			}
			return attributeDialog;
		}

		 public void actionPerformed(ActionEvent e) {
		     if (getAttributeDialog().isVisible() == false && getMapModule() != null)
		     {
		         getAttributeDialog().pack();
		         getAttributeDialog().show();
		     }
		}
    }

    private class ShowFilterToolbarAction extends AbstractAction {
        ShowFilterToolbarAction(Controller controller) {
            super(null,
                  new ImageIcon(getResource("images/filter.gif")));
        }
        public void actionPerformed(ActionEvent event) {
            JToggleButton btnFilter = (JToggleButton)event.getSource();
            if(btnFilter.getModel().isSelected()){
                getFilterController().showFilterToolbar(true);
            }
            else{
                getFilterController().showFilterToolbar(false);
            }
        }
    }

    private class NavigationNextMapAction extends AbstractAction {
        NavigationNextMapAction(Controller controller) {
            super(controller.getResourceString("next_map"),
                  new ImageIcon(getResource("images/1rightarrow.png")));
            setEnabled(false);
        }
        public void actionPerformed(ActionEvent event) {
            mapModuleManager.nextMapModule();
        }
    }

    //
    // Node navigation
    //

    private class MoveToRootAction extends AbstractAction {
        MoveToRootAction(Controller controller) {
            super(controller.getResourceString("move_to_root"));
            setEnabled(false);
        }
        public void actionPerformed(ActionEvent event) {
            moveToRoot();
        }
    }

    private class ToggleMenubarAction extends AbstractAction  implements MenuItemSelectedListener  {
        ToggleMenubarAction(Controller controller) {
           super(controller.getResourceString("toggle_menubar"));
           setEnabled(true);
        }
        public void actionPerformed(ActionEvent event) {
           menubarVisible=!menubarVisible;
           setMenubarVisible(menubarVisible);
        }
		public boolean isSelected(JMenuItem pCheckItem, Action pAction) {
			return menubarVisible;
		}
    }

    private class ToggleToolbarAction extends AbstractAction implements MenuItemSelectedListener {
        ToggleToolbarAction(Controller controller) {
           super(controller.getResourceString("toggle_toolbar"));
           setEnabled(true);
        }
        public void actionPerformed(ActionEvent event) {
           toolbarVisible=!toolbarVisible;
           setToolbarVisible(toolbarVisible);
        }
		public boolean isSelected(JMenuItem pCheckItem, Action pAction) {
			logger.info("ToggleToolbar was asked for selectedness.");
			return toolbarVisible;
		}
    }

    private class ToggleLeftToolbarAction extends AbstractAction implements MenuItemSelectedListener {
        ToggleLeftToolbarAction(Controller controller) {
           super(controller.getResourceString("toggle_left_toolbar"));
           setEnabled(true);
        }
        public void actionPerformed(ActionEvent event) {
           leftToolbarVisible=!leftToolbarVisible;
           setLeftToolbarVisible(leftToolbarVisible);
        }
		public boolean isSelected(JMenuItem pCheckItem, Action pAction) {
			return leftToolbarVisible;
		}
    }
    
    public void recurSetSlideShowInfo(NodeAdapter curNode){
    	NodeAdapter cur = curNode;
    	NodeAdapter prev;// = prevNode;
    	NodeAdapter next;// = nextNode;
    	NodeAdapter root = (NodeAdapter)mc.getRootNode();
    	int i;
    	
    	int cnt = curNode.getChildCount();
    	
    	if(cur.getNodeTypeStr().equals("Question") || cur.getNodeTypeStr().equals("Ticket"))
    		return;

    	prev = recurGetPrev(root, root);
    	next = recurGetNext(root, root);
    	
    	cur.setPrev(prev);
    	cur.setNext(next);
    	
    	for(i = 0; i < cnt; i++){ // set the others
    		recurSetSlideShowInfo((NodeAdapter)curNode.getChildAt(i));
    	}
    }
    
    public NodeAdapter recurGetPrev(NodeAdapter prevNode, NodeAdapter curNode){
   		cur = curNode;
   		
    	if(prevNode.getNodeTypeStr().equals("Slide") || prevNode.getNodeTypeStr().equals("Survey"))
    		prev = prevNode;
    	
    	int i;

    	int cnt = curNode.getChildCount();

    	if( (cur.getNodeTypeStr().equals("Slide") || cur.getNodeTypeStr().equals("Survey")) && cur.getPrev() == null && cur.getNext() == null)
    		return prev;
    	
    	for(i = 0; i < cnt; i++){
    		NodeAdapter tmp;
    		tmp = recurGetPrev(cur, (NodeAdapter)curNode.getChildAt(i));
    		if(tmp != null)
    			return tmp;
    	}
    	return null; // last node
    }
    
    public NodeAdapter recurGetNext(NodeAdapter prevNode, NodeAdapter curNode){
   		cur = curNode;
   		
    	if(prevNode.getNodeTypeStr().equals("Slide") || prevNode.getNodeTypeStr().equals("Survey"))
    		prev = prevNode;
    	
    	int i;

    	int cnt = curNode.getChildCount();
    	
    	if( (cur.getNodeTypeStr().equals("Slide") || cur.getNodeTypeStr().equals("Survey")) && prev.getNext() == null)
    		return cur;
    	
    	for(i = 0; i < cnt; i++){
    		NodeAdapter tmp;
    		tmp = recurGetNext(cur, (NodeAdapter)curNode.getChildAt(i));
    		if(tmp != null)
    			return tmp;
    	}
    	return null; // last node
    }
    
    

    protected class ZoomInAction extends AbstractAction {
        public ZoomInAction(Controller controller) {
           super(controller.getResourceString("zoom_in")); }
        public void actionPerformed(ActionEvent e) {
//            logger.info("ZoomInAction actionPerformed");
           ((MainToolBar)toolbar).zoomIn(); }}

    protected class ZoomOutAction extends AbstractAction {
        public ZoomOutAction(Controller controller) {
           super(controller.getResourceString("zoom_out")); }
        public void actionPerformed(ActionEvent e) {
//            logger.info("ZoomOutAction actionPerformed");
           ((MainToolBar)toolbar).zoomOut(); }}

    protected class ShowSelectionAsRectangleAction extends AbstractAction implements MenuItemSelectedListener{
        public ShowSelectionAsRectangleAction(Controller controller) {
           super(controller.getResourceString("selection_as_rectangle")); }
        public void actionPerformed(ActionEvent e) {
//            logger.info("ShowSelectionAsRectangleAction action Performed");
            toggleSelectionAsRectangle();
           }
		public boolean isSelected(JMenuItem pCheckItem, Action pAction) {
			return isSelectionAsRectangle();
		}
    }

     private class ShowAllAttributesAction extends AbstractAction {
        public ShowAllAttributesAction(){
            super(Resources.getInstance().getResourceString("attributes_show_all"));
        };
        public void actionPerformed(ActionEvent e) {
            final MindMap map = getMap();
            setAttributeViewType(map);
        }
        public void setAttributeViewType(final MindMap map) {
            final AttributeRegistry attributes = map.getRegistry().getAttributes();
            if(attributes.getAttributeViewType() != AttributeTableLayoutModel.SHOW_ALL){
                attributes.setAttributeViewType(AttributeTableLayoutModel.SHOW_ALL);
            }
        }
    }
    private class HideAllAttributesAction extends AbstractAction {
        public HideAllAttributesAction(){
            super(Resources.getInstance().getResourceString("attributes_hide_all"));
        };
        public void actionPerformed(ActionEvent e) {
            final MindMap map = getMap();
            setAttributeViewType(map);
        }
        public void setAttributeViewType(final MindMap map) {
            final AttributeRegistry attributes = map.getRegistry().getAttributes();
            if(attributes.getAttributeViewType() != AttributeTableLayoutModel.HIDE_ALL){
                attributes.setAttributeViewType(AttributeTableLayoutModel.HIDE_ALL);
            }
        }
    }

    private class ShowSelectedAttributesAction extends AbstractAction {
        public ShowSelectedAttributesAction(){
            super(Resources.getInstance().getResourceString("attributes_show_selected"));
        };
        public void actionPerformed(ActionEvent e) {
            MindMap map = getMap();
            setAttributeViewType(map);
        }
        void setAttributeViewType(MindMap map){
            final AttributeRegistry attributes = map.getRegistry().getAttributes();
            if(attributes.getAttributeViewType() != AttributeTableLayoutModel.SHOW_SELECTED){
                attributes.setAttributeViewType(AttributeTableLayoutModel.SHOW_SELECTED);
            }
        }
    }

    //
    // Preferences
    //

    private static Vector propertyChangeListeners = new Vector();

    private AttributeManagerDialog attributeDialog = null;

    public static Collection getPropertyChangeListeners() {
        return Collections.unmodifiableCollection(propertyChangeListeners);
    }
    public void toggleSelectionAsRectangle() {
    	if(isSelectionAsRectangle()){
    		setProperty(FreeMind.RESOURCE_DRAW_RECTANGLE_FOR_SELECTION, BooleanProperty.FALSE_VALUE);
    	}
    	else{
    		setProperty(FreeMind.RESOURCE_DRAW_RECTANGLE_FOR_SELECTION, BooleanProperty.TRUE_VALUE);
    	}
	}

	private boolean isSelectionAsRectangle() {
		return getProperty(FreeMind.RESOURCE_DRAW_RECTANGLE_FOR_SELECTION).equalsIgnoreCase(BooleanProperty.TRUE_VALUE);
	}

	/**
     */
    public MindMap getMap() {
        return getMapModule().getModel();
    }

    public static void addPropertyChangeListener(FreemindPropertyListener listener) {
        Controller.propertyChangeListeners.add(listener);
    }

    /**
     * @param listener The new listener. All currently available properties are sent to
     * the listener after registration. Here, the oldValue parameter is set to null.
     */
    public static void addPropertyChangeListenerAndPropagate(FreemindPropertyListener listener) {
    	Controller.addPropertyChangeListener(listener);
    	Properties properties = Resources.getInstance().getProperties();
    	for (Iterator it = properties.keySet().iterator(); it.hasNext();) {
    		String key = (String) it.next();
    		listener.propertyChanged(key, properties.getProperty(key), null);
    	}
    }

    public static void removePropertyChangeListener(FreemindPropertyListener listener) {
        Controller.propertyChangeListeners.remove(listener);
    }
	/**
	 * @author foltin
	 *
	 */
	public class PropertyAction extends AbstractAction {

		private final Controller controller;

		/**
		 *
		 */
		public PropertyAction(Controller controller) {
			super(controller.getResourceString("property_dialog"));
			this.controller = controller;
		}

		public void actionPerformed(ActionEvent arg0) {
			JDialog dialog = new JDialog(getFrame().getJFrame(), true /* modal */);
			dialog.setResizable(true);
			dialog.setUndecorated(false);
			final OptionPanel options = new OptionPanel((FreeMind)getFrame(), dialog, new OptionPanelFeedback() {

				public void writeProperties(Properties props) {
					Vector sortedKeys = new Vector();
					sortedKeys.addAll(props.keySet());
					Collections.sort(sortedKeys);
					boolean propertiesChanged = false;
					for (Iterator i = sortedKeys.iterator(); i.hasNext();) {
						String key = (String) i.next();
						// save only changed keys:
                        String newProperty = props.getProperty(key);
                        propertiesChanged = propertiesChanged || ! newProperty.equals(controller.getProperty(key));
						controller.setProperty(key, newProperty);
					}

					if (propertiesChanged ) {
                        JOptionPane
                                .showMessageDialog(
                                        null,
                                        getResourceString("option_changes_may_require_restart"));
                        controller.getFrame().saveProperties();
                    }
				}
			});
			options.buildPanel();
			options.setProperties();
			dialog.setTitle("Freemind Properties");
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter(){
			    public void windowClosing(WindowEvent event) {
			        options.closeWindow();
			    }
			});
			Action action = new AbstractAction() {

				public void actionPerformed(ActionEvent arg0) {
			        options.closeWindow();
				}
			};
			Tools.addEscapeActionToDialog(dialog, action);
			dialog.setVisible(true);

		}


	}

	private class BackgroundSwatch extends ColorSwatch {
        Color getColor() {
            return getView().getBackground();
        }
    }

    public class OptionAntialiasAction extends AbstractAction implements FreemindPropertyListener {
       OptionAntialiasAction(Controller controller) {
           Controller.addPropertyChangeListener(this);
       }
       public void actionPerformed(ActionEvent e) {
          String command = e.getActionCommand();
        changeAntialias(command);
       }
	    /**
	     */
	    public void changeAntialias(String command) {
	        if(command == null) {
	            return;
	        }
	        if (command.equals("antialias_none")) {
	             setAntialiasEdges(false);
	             setAntialiasAll(false); }
	          if (command.equals("antialias_edges")) {
	             setAntialiasEdges(true);
	             setAntialiasAll(false); }
	          if (command.equals("antialias_all")) {
	             setAntialiasEdges(true);
	             setAntialiasAll(true); }
	          if(getView() != null)
	              getView().repaint();
	    }
	    public void propertyChanged(String propertyName, String newValue, String oldValue) {
            if (propertyName.equals(FreeMindCommon.RESOURCE_ANTIALIAS)) {
                changeAntialias(newValue);
            }
	    }
    }

    private class OptionHTMLExportFoldingAction extends AbstractAction {
       OptionHTMLExportFoldingAction(Controller controller) {}
       public void actionPerformed(ActionEvent e) {
          setProperty("html_export_folding", e.getActionCommand()); }}

    // switch auto properties for selection mechanism fc, 7.12.2003.
    private class OptionSelectionMechanismAction extends AbstractAction implements FreemindPropertyListener {
        Controller c;

        OptionSelectionMechanismAction(Controller controller) {
            c = controller;
            Controller.addPropertyChangeListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            changeSelection(command);
        }

        /**
         */
        private void changeSelection(String command) {
            setProperty("selection_method", command);
            // and update the selection method in the NodeMouseMotionListener
            c.getNodeMouseMotionListener().updateSelectionMethod();
            String statusBarString = c.getResourceString(command);
            if (statusBarString != null) // should not happen
                c.getFrame().out(statusBarString);
        }

        public void propertyChanged(String propertyName, String newValue, String oldValue) {
            if(propertyName.equals(FreeMind.RESOURCES_SELECTION_METHOD)) {
                changeSelection(newValue);
            }
        }
    }

    // open faq url from freeminds page:
    private class OpenURLAction extends AbstractAction {
        Controller c;
        private final String url;
        OpenURLAction(Controller controller, String description, String url) {
            super(description, new ImageIcon(controller.getResource("images/Link.png")));
            c = controller;
            this.url = url;
        }
        public void actionPerformed(ActionEvent e) {
            try {
                c.getFrame().openDocument(new URL(url));
            } catch (MalformedURLException ex) {
                c.errorMessage(c.getResourceString("url_error")+"\n"+ex);
            } catch (Exception ex) {
                c.errorMessage(ex);
            }
        }
    }


    public FilterController getFilterController() {
        return mFilterController;
    }

    public PageFormat getPageFormat() {
        return pageFormat;
    }
    public void setAttributeViewType(MindMap map, String value) {
        if(value.equals(AttributeTableLayoutModel.SHOW_SELECTED)){
            ((ShowSelectedAttributesAction)showSelectedAttributes).setAttributeViewType(map);
        }
        else if(value.equals(AttributeTableLayoutModel.HIDE_ALL)){
            ((HideAllAttributesAction)hideAllAttributes).setAttributeViewType(map);
        }
        else if(value.equals(AttributeTableLayoutModel.SHOW_ALL)){
            ((ShowAllAttributesAction)showAllAttributes).setAttributeViewType(map);
        }
    }

	public Object setEdgesRenderingHint(Graphics2D g) {
		Object renderingHint = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, (getAntialiasEdges())?RenderingHints.VALUE_ANTIALIAS_ON:RenderingHints.VALUE_ANTIALIAS_OFF);
		return renderingHint; 
	}

	public void setTextRenderingHint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, (getAntialiasAll())?RenderingHints.VALUE_TEXT_ANTIALIAS_ON:RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, (getAntialiasAll())?RenderingHints.VALUE_ANTIALIAS_ON:RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	

}

