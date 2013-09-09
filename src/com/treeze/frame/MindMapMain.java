package com.treeze.frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;

import JDIalog.JDialogSurvey;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.treeze.data.ClassInfo;
import com.treeze.data.CurrentPositionOfNav;
import com.treeze.data.JsonTicket;
import com.treeze.data.MindNode;
import com.treeze.data.NaviInfo;
import com.treeze.data.ServerSocket;
import com.treeze.data.Ticket;
import com.treeze.data.TreezeData;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.User;
import com.treeze.data.Survey.Survey;
import com.treeze.downloadthread.DownLoadAllTicket;

public class MindMapMain extends JPanel {
	static MindNode md;
	final int NODE_WIDTH = 80;
	final int NODE_HEIGHT = 30;
	final int SCROLLFRAME_WIDTH = 4000;
	final int SCROLLFRAME_HEIGHT = 2000;

	ImageIcon imgIcon;
	Image img;
	Image scaledImage;
	BufferedImage imageBuff;
	ImageIcon resizeIcon;
	Dimension dimension = new Dimension();
	private ArrayList<MindNode> nodes = new ArrayList<MindNode>();
	MindNode root;
	MainFrameManager mainFrameManager;
	

	JScrollPane jsp;
	JButton j;
	public ScrollPanel nodeScrollPanel = new ScrollPanel();
	CubicCurve2D.Float graph = new CubicCurve2D.Float();
	java.awt.Dimension screenSize;
	
	Graphics g;
	ClassInfo classinfo;
	User user;

	/**
	 * @param args
	 */
	public MindMapMain(String xml, ClassInfo classinfo) {
		// TODO Auto-generated constructor stub
		screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//setTitle(classinfo.getClassName());
		this.classinfo = classinfo;
	//	setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.user = user;
		MindNode.setClassinfo(classinfo);

	//	this.getContentPane().setBackground(Color.white);
		

		this.setLayout(new BorderLayout());


		XStream xstream = new XStream();
		xstream.processAnnotations(MindMap.class);
		xstream.alias("icon", Icon.class);
		
		
		MindMap map = (MindMap) xstream.fromXML(xml);

		root = new MindNode(map.nodes.get(0).NODEID,map.nodes.get(0).getTEXT(),
				(int) SCROLLFRAME_WIDTH/2, SCROLLFRAME_HEIGHT/2,this);
		nodes.add(root);
		for (int i = 0; i < map.nodes.get(0).nodes.size(); i++) {
			nodetoMindNode(root, map.nodes.get(0).nodes.get(i));
		}
		DownLoadAllTicket downLoadAllTicket = new DownLoadAllTicket(classinfo);
		downLoadAllTicket.start();
		
		nodeScrollPanel = new ScrollPanel(); // 占썬�占썸에?占쏙옙 占쏙옙占썩�占�占쏙옙
		jsp = new JScrollPane(nodeScrollPanel); // 占썬�占썸에占쏙옙?占쏙옙占쏙옙?占쏙옙
		
		this.add(jsp);

		//setSize(screenSize.width, screenSize.height);

		SocketThread socketThread = new SocketThread(classinfo);
		socketThread.start();
		
		setVisible(true);
		init(); // 占썬�占썸에占쏙옙占쏙옙占쏙옙占�(?占썩�占썹�占썰빳占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙);

	
		

	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		dimension = getSize();
		init();
		System.out.println("[MindMapMain getWidth]  = "+dimension.getWidth());
	}

	public void init() {
		
		jsp.getHorizontalScrollBar().setValue(2000);
		jsp.getVerticalScrollBar().setValue((int) (root.getLocateY()-dimension.getHeight()/2));
		jsp.getHorizontalScrollBar().setValue((int) (root.getLocateX()-dimension.getWidth()/2));

	}


	class ScrollPanel extends JPanel {
		private Point pp = new Point();

		public ScrollPanel() {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			this.setBackground(Color.WHITE);
			this.add(new JButton("dmdk"));
			for (MindNode node : nodes) {
				// if(node==MindNode.getRoot()||node.getParentNode()==MindNode.getRoot()){
				// node.getNodeBtn().setIcon(rootnodeImgIcon);
				// }
				// else{
				// node.getNodeBtn().setIcon(nodeImgIcon);
				// }
				ImageIcon ticketNewIcon  = TreezeStaticData.makeResizedImageIcon(20, 20, TreezeStaticData.TICKET_NEW_IMG);
				node.getTicketBtn().setIcon(ticketNewIcon);
				node.getNodeBtn().setBounds(node.getLocateX(),
						node.getLocateY(), NODE_WIDTH, NODE_HEIGHT);
				node.getPptBtn().setBounds(node.getLocateX(),
						node.getLocateY()-10, 20, 20);
				node.getTicketBtn().setBounds(node.getEndX()-20,
						node.getLocateY()-20, 20, 20);
				node.getNodeBtn().setFont(new Font("Serif",Font.BOLD,10));
				node.getNodeBtn().setText(node.getNodeStr());
				node.getNodeBtn()
						.setVerticalTextPosition(SwingConstants.CENTER);
				node.getNodeBtn().setHorizontalTextPosition(
						SwingConstants.CENTER);
				
				node.getNodeBtn().setBackground(new Color(0, 0, 0, 0));
				
				node.getTicketBtn().setBorderPainted(false);
				node.getTicketBtn().setBorder(null);
				node.getTicketBtn().setContentAreaFilled(false);
				node.getTicketBtn().setFocusPainted(false); 
				node.getTicketBtn().setOpaque(false);
				
				node.getNodeBtn().setBorderPainted(false);
				node.getNodeBtn().setBorder(null);
				node.getNodeBtn().setContentAreaFilled(false);
				node.getNodeBtn().setFocusPainted(false); 
				node.getNodeBtn().setOpaque(false);
				//node.getNodeBtn().setMargin(new IØnsets(5, 5, 5, 5));
				
				
				this.add(node.getNodeBtn());
				this.add(node.getTicketBtn());
				node.getTicketBtn().setVisible(false);
				//this.setComponentZOrder(this.getGraphics(), 0);
//				this.setComponentZOrder(node.getNodeBtn(), 1);
//				this.setComponentZOrder(node.j, 0);


			}
			this.setPreferredSize(new Dimension(SCROLLFRAME_WIDTH,
					SCROLLFRAME_HEIGHT));

			final Rectangle rect = new Rectangle();
			final Point move = new Point();
			MindNode node = MindNode.getRoot();
			MouseInputAdapter mia = new MouseInputAdapter() {
				int m_XDifference, m_YDifference;
				boolean m_dragging;

				public void mouseDragged(MouseEvent e) {

					JViewport vport = jsp.getViewport();

					Point cp = e.getPoint();

					Point vp = vport.getViewPosition();
					vp.translate((pp.x - cp.x) / 2, (pp.y - cp.y) / 2);
					scrollRectToVisible(new Rectangle(vp, vport.getSize()));

				}

				public void mousePressed(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					m_XDifference = e.getX();
					m_YDifference = e.getY();

					pp = e.getPoint();
					System.out.println("MindMap Panel Mouse Click x = "
							+ e.getX() + "y = " + e.getY());
					
					//asd.setLocationRelativeTo(MindMapMain.this);
					
					// jsp.getHorizontalScrollBar().setValue(4000);
				}

				public void mouseReleased(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				}
			};
			addMouseMotionListener(mia);
			addMouseListener(mia);

		}

	

	
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			
	//		System.out.println("MindMap PaintComponents");
			Graphics2D g2 = (Graphics2D) g;
			// g2.drawLine(100, 100, 1001, 1010);
		//	g2.scale(0.9, 0.9);
			g2.setStroke(new BasicStroke(2));
			for (MindNode node : nodes) {
				if (node.getParentNode() != null) {
					if (node.getDirection() == 0) {
						graph.setCurve((node.getParentNode().getEndX())-50, node
								.getParentNode().getMiddleY(), (node
								.getParentNode().getEndX()), node
								.getParentNode().getMiddleY(), node
								.getLocateX() - 80, node.getMiddleY(), node
								.getLocateX(), node.getMiddleY());
					} else {

						graph.setCurve(node.getEndX(), node.getMiddleY(),

						node.getEndX() + 90, node.getMiddleY(), node
								.getParentNode().getLocateX(), node
								.getParentNode().getMiddleY(),

						node.getParentNode().getLocateX()+50,

						node.getParentNode().getMiddleY());

					}
					if (node.isPassed()) {

						g2.setColor(new Color(37, 170, 226));
						g2.setStroke(new BasicStroke(5));
					} else {

						g2.setColor(new Color(0, 0, 0));
						g2.setStroke(new BasicStroke(3));
					}

					g2.draw(graph);

				}
			}

		}
		
	}
	public MainFrameManager getMainFrameManager() {
		return mainFrameManager;
	}
	public void setMainFrameManager(MainFrameManager mainFrameManager) {
		this.mainFrameManager = mainFrameManager;
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreezeStaticData t = new TreezeStaticData();
		 LoginPageFrame l = new LoginPageFrame();
		 l.setVisible(true);

	}

	public void nodetoMindNode(MindNode parent, node child) {
		if(child.getNODETYPESTR().equals("Question")) return;
		MindNode temp = new MindNode(parent, child.NODEID,child.getTEXT(),
				child.getPOSITION(),child.IMGPATH);
		nodes.add(temp);
		if (child.nodes != null)
			for (int i = 0; i < child.nodes.size(); i++) {
				nodetoMindNode(temp, child.nodes.get(i));
			}

	}

	class SocketThread extends Thread {

		InputStream inputStream;

		Socket serverSocket;
		ClassInfo classInfo;

		public SocketThread(ClassInfo classInfo) {
			// TODO Auto-generated constructor stub
			this.classInfo = classInfo;
		}

		public void run() {

			String ip = TreezeStaticData.IP;
			int port = 2141;
			Socket socket;
			String str;
			try {
				socket = new Socket(ip, port);
				Gson gson = new Gson();
				
				System.out.println("[Socket Start]");
				ServerSocket sv = ServerSocket.getInstance();
				sv.setSocket(socket);
				// ClassInfo classInfo = new ClassInfo();
				TreezeData treezeData = new TreezeData();
				User user = User.getInstance();
				// user 쨉?占승올��� 쨘쨘?占썩�
				user.setUserType(User.STUDENT);
				

				// classInfo 쨉?占승올��� 쨘쨘?占썩�
				classInfo.setClassName("������");

				// treezeData 쨉?占승올��� 쨘쨘?占썩�
				treezeData.setDataType("connectionInfo");
				treezeData.getArgList().add(gson.toJson(user));
				treezeData.getArgList().add(gson.toJson(classInfo));

				// IO Stream 쨘쨘?占썩�
				InputStream is;
				try {
					is = socket.getInputStream();
					java.io.OutputStream os = socket.getOutputStream();
					
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw);
					BufferedReader in = new BufferedReader(new InputStreamReader(is));

					// 쨘?占쏙옙?占시몌옙?쩔쨍쨘??
					os.write((gson.toJson(treezeData).getBytes("UTF-8")));
					os.flush();
					byte[] b = new byte[1024];
					// 쨘?占쏙옙?占승울옙?쩔쩔짜??
					
					int cnt  = is.read(b);
					str = str = new String(b, 0, cnt, "UTF-8");
					System.out.println("server send : " + str);

					// 쩔�廓?占승≤㎳￠�몌옙?
					// Scanner scan = new Scanner(System.in);
					// scan.next();
					int a = 0;
					
					while (true) {
						 cnt = is.read(b);
						 System.out.println(a++);
						if(cnt== -1){
							System.out.println("Socket End");
							return;
						}
						str = null;
						try {
							str = new String(b, 0, cnt, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(str);
						java.lang.reflect.Type type = new TypeToken<TreezeData>() {
						}.getType();
						TreezeData jsonResultTreezeData = (TreezeData) gson
								.fromJson(str, (java.lang.reflect.Type) type);
						System.out.println(jsonResultTreezeData.getArgList()
								.get(0));
						if (jsonResultTreezeData.getDataType().equals(
								TreezeData.NAVI)) {
							
							NaviInfo naviInfo = (NaviInfo) gson
									.fromJson(jsonResultTreezeData
											.getArgList().get(0), NaviInfo.class);
							
							MindNode naviNode =  MindNode.getNodeuseNodeID(MindNode.getRoot(),naviInfo.getNodeID());
							
								MindNode.setNav(naviNode);
							
							
							
							
							repaint();
							
						} else if (jsonResultTreezeData.getDataType().equals(
								TreezeData.SURVEY)) {
							type = new TypeToken<Survey>() {
							}.getType();
							Survey survey = (Survey) gson
									.fromJson(jsonResultTreezeData
											.getArgList().get(0), Survey.class);
							JDialogSurvey surveyDialog  = new JDialogSurvey(survey,os);
							surveyDialog.setLocationRelativeTo(MindMapMain.this);
							
							
							//System.out.println("Survey");
						} else if(jsonResultTreezeData.getDataType().equals(TreezeData.TICKET)){
							System.out.println("Ticket");
						
							
							JsonTicket ticket = (JsonTicket) gson
									.fromJson(jsonResultTreezeData
											.getArgList().get(0), JsonTicket.class);
							
							MindNode parentNode =  MindNode.getNodeuseNodeID(MindNode.getRoot(),ticket.getticket().getParentNodeId());
							
							new Ticket(parentNode, ticket.getticket().getId()+"",ticket.getticket().getContents(), ticket.getticket().getUserName());
							
							MindNode node = parentNode;
							while(node instanceof Ticket){
								node = node.getParentNode();
							}
							node.getTicketBtn().setVisible(true); // New Icon add
							mainFrameManager.ticketRepaint(node);
							
							
							
						}
					
					}
					
				//	pw.close();
					//socket.close();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}

			// naviInputStream = naviSocket.getInputStream();
		}
	}
	
}

@XStreamAlias("map")
class MindMap{
	@XStreamAsAttribute
	String version;
	
	@XStreamImplicit(itemFieldName="node")
	ArrayList<node> nodes;
	
}

@XStreamAlias("icon")
class Icon implements Cloneable{
	
	@XStreamAsAttribute
	String BUILTIN;
}
@XStreamAlias("node")
class node implements Cloneable {


	@XStreamImplicit(itemFieldName="node")
	ArrayList<node> nodes;
	
	@XStreamImplicit(itemFieldName="icon")
	ArrayList<Icon> icons;

	@XStreamAsAttribute
	String CREATED;
	@XStreamAsAttribute
	String NODEID;
	@XStreamAsAttribute
	String ID;
	@XStreamAsAttribute
	String MODIFIED;
	@XStreamAsAttribute
	String POSITION;
	@XStreamAsAttribute
	String TEXT;
	@XStreamAsAttribute
	String IMGPATH;
	@XStreamAsAttribute
	String NODETYPESTR;
	
	public ArrayList<Icon> getIcon() {
		return icons;
	}

	public void setIcon(ArrayList<Icon> icon) {
		this.icons = icon;
	}
//icon
	public String getNODETYPESTR() {
		return NODETYPESTR;
	}

	public void setNODETYPESTR(String nODETYPESTR) {
		NODETYPESTR = nODETYPESTR;
	}

	public String getIMGPATH() {
		return IMGPATH;
	}

	public void setIMGPATH(String iMGPATH) {
		IMGPATH = iMGPATH;
	}

	public String getCREATED() {
		return CREATED;
	}

	public void setCREATED(String cREATED) {
		CREATED = cREATED;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}

	public String getTEXT() {
		return TEXT;
	}

	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
}