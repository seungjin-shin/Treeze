package freemind.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import freemind.Frame.ProfileFrame;
import freemind.json.Lecture;
import freemind.json.Ticket;
import freemind.json.User;
import freemind.modes.NodeAdapter;
import freemind.modes.mindmapmode.MindMapController;
import freemind.modes.mindmapmode.MindMapMapModel;

public class FreemindManager {
	private static FreemindManager fInstance;
//	public String SERVERIP = "113.198.84.80";
	public String SERVERIP = "14.63.215.88";
	
	public int PORT = 2141;

	public static FreemindManager getInstance(){
		if(fInstance == null){
			fInstance = new FreemindManager();
		}
		return fInstance;
	}
	
	private FreemindManager(){
		slideShow = new SlideShow(this);
		user = new User();
		user.setUserEmail("minsuk@hansung.ac.kr");
		user.setUserName("¿ÃπŒºÆ");
	}
	
	
	//(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));
	public Image treezeLogo = new ImageIcon(getClass().getClassLoader().getResource("images/treezeLogo.png")).getImage();
	public Image loginInputBar = new ImageIcon(getClass().getClassLoader().getResource("images/LoginInputBar.png")).getImage();
	public Image activateBar = new ImageIcon(getClass().getClassLoader().getResource("images/activatebar.png")).getImage();
	public Image professorImg = new ImageIcon(getClass().getClassLoader().getResource("images/minsuk.jpg")).getImage();
	public Image defaultProfile = new ImageIcon(getClass().getClassLoader().getResource("images/profiledefault.png")).getImage();
	
	public Image loginDefault = new ImageIcon(getClass().getClassLoader().getResource("images/loginBtn1.png")).getImage();
	public Image loginOver = new ImageIcon(getClass().getClassLoader().getResource("images/loginBtn2.png")).getImage();
	public Image loginPress = new ImageIcon(getClass().getClassLoader().getResource("images/loginBtn3.png")).getImage();
	public Image writeDefault = new ImageIcon(getClass().getClassLoader().getResource("images/write1.png")).getImage();
	public Image writeOver = new ImageIcon(getClass().getClassLoader().getResource("images/write2.png")).getImage();
	public Image writePress = new ImageIcon(getClass().getClassLoader().getResource("images/write3.png")).getImage();
	public Image closeDefault = new ImageIcon(getClass().getClassLoader().getResource("images/close1.png")).getImage();
	public Image closeOver = new ImageIcon(getClass().getClassLoader().getResource("images/close2.png")).getImage();
	public Image closePress = new ImageIcon(getClass().getClassLoader().getResource("images/close3.png")).getImage();
	public Image sendDefault = new ImageIcon(getClass().getClassLoader().getResource("images/send1.png")).getImage();
	public Image sendOver = new ImageIcon(getClass().getClassLoader().getResource("images/send2.png")).getImage();
	public Image sendPress = new ImageIcon(getClass().getClassLoader().getResource("images/send3.png")).getImage();
	public Image addLectureDefault = new ImageIcon(getClass().getClassLoader().getResource("images/addlecture1.png")).getImage();
	public Image addLectureOver = new ImageIcon(getClass().getClassLoader().getResource("images/addlecture2.png")).getImage();
	public Image addLecturePress = new ImageIcon(getClass().getClassLoader().getResource("images/addlecture3.png")).getImage();
	public Image deleteLectureDefault = new ImageIcon(getClass().getClassLoader().getResource("images/deletelecture1.png")).getImage();
	public Image deleteLectureOver = new ImageIcon(getClass().getClassLoader().getResource("images/deletelecture2.png")).getImage();
	public Image deleteLecturePress = new ImageIcon(getClass().getClassLoader().getResource("images/deletelecture3.png")).getImage();
	public Image lectureListDefault = new ImageIcon(getClass().getClassLoader().getResource("images/lecturelist1.png")).getImage();
	public Image lectureListOver = new ImageIcon(getClass().getClassLoader().getResource("images/lecturelist2.png")).getImage();
	public Image lectureListPress = new ImageIcon(getClass().getClassLoader().getResource("images/lecturelist3.png")).getImage();
	public Image addClassDefault = new ImageIcon(getClass().getClassLoader().getResource("images/addclass1.png")).getImage();
	public Image addClassOver = new ImageIcon(getClass().getClassLoader().getResource("images/addclass2.png")).getImage();
	public Image addClassPress = new ImageIcon(getClass().getClassLoader().getResource("images/addclass3.png")).getImage();
	public Image deleteClassDefault = new ImageIcon(getClass().getClassLoader().getResource("images/deleteclass1.png")).getImage();
	public Image deleteClassOver = new ImageIcon(getClass().getClassLoader().getResource("images/deleteclass2.png")).getImage();
	public Image deleteClassPress = new ImageIcon(getClass().getClassLoader().getResource("images/deleteclass3.png")).getImage();
	public Image profileDefault = new ImageIcon(getClass().getClassLoader().getResource("images/profile1.png")).getImage();
	public Image profileOver = new ImageIcon(getClass().getClassLoader().getResource("images/profile2.png")).getImage();
	public Image profilePress = new ImageIcon(getClass().getClassLoader().getResource("images/profile3.png")).getImage();
	public Image signDefault = new ImageIcon(getClass().getClassLoader().getResource("images/sign1.png")).getImage();
	public Image signOver = new ImageIcon(getClass().getClassLoader().getResource("images/sign2.png")).getImage();
	public Image signPress = new ImageIcon(getClass().getClassLoader().getResource("images/sign3.png")).getImage();
	
	public Image regFalse = new ImageIcon(getClass().getClassLoader().getResource("images/regmindmap2.png")).getImage();
	public Image regDefault = new ImageIcon(getClass().getClassLoader().getResource("images/regmindmap1.png")).getImage();
	public Image regOver = new ImageIcon(getClass().getClassLoader().getResource("images/regmindmap3.png")).getImage();
	public Image regPress = new ImageIcon(getClass().getClassLoader().getResource("images/regmindmap4.png")).getImage();
	public Image startFalse = new ImageIcon(getClass().getClassLoader().getResource("images/startlecture2.png")).getImage();
	public Image startDefault = new ImageIcon(getClass().getClassLoader().getResource("images/startlecture1.png")).getImage();
	public Image startOver = new ImageIcon(getClass().getClassLoader().getResource("images/startlecture3.png")).getImage();
	public Image startPress = new ImageIcon(getClass().getClassLoader().getResource("images/startlecture4.png")).getImage();
	
	
	MindMapController mc;
	Controller c;
	
	OutputStream os;
	InputStream in;
	
	private boolean isSlideShowInfo = false;
	
	public String mode; 
	public static String REGMODE = "regMode";
	public static String LECMODE = "lecMode";
	
	private MenuBar menuBar;

	private String filePath;// = "/Users/dewlit/Desktop/test/Linux/";
	private String downPath = System.getProperty("user.home") + System.getProperty("file.separator") + "Treeze";
	private String nodeText = "";
	


	private int classId = 1;
	private int pdfPage;
	private MindMapMapModel mModel;
	private ProfileFrame profileFrame;
	private JFrame freemindMainFrame;
	private Ticket ticket;
	private Lecture lecture;
	


	SlideShow slideShow;
	
	public Color treezeColor = new Color(141, 198, 63);
	public Color noColor = new Color(0, 0, 0, 0);
	
	private NodeAdapter receiveQNode;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lecture getLecture() {
		return lecture;
	}
	
	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
	public ImageIcon makeResizedImageIcon(int width, int height, Image imgs) {

		BufferedImage imageBuff = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Image img = imgs;
		Graphics g = imageBuff.createGraphics();
		Image scaleImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		g.drawImage(scaleImg, 0, 0, new Color(0, 0, 0), null);
		return new ImageIcon(scaleImg);

	}
	
	public NodeAdapter getReceiveQNode() {
		return receiveQNode;
	}

	public void setReceiveQNode(NodeAdapter receiveQNode) {
		this.receiveQNode = receiveQNode;
	}

	public String getNodeText() {
		return nodeText;
	}
	
	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}
	public void setEnableMenuBar(){
		if(getMode().equals(REGMODE)){
			menuBar.setRegModeMenu();
		}
		else{
			menuBar.setLecModeMenu();
		}
	}
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void init(){
		isSlideShowInfo = false;
		
		try {
			if(in != null)
				in.close();
			if(os != null)
				os.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Ticket getTicket() {
		return ticket;
	}
	
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public int getPORT() {
		return PORT;
	}
	
	public void setPORT(int pORT) {
		PORT = pORT;
	}
	public void setSERVERIP(String sERVERIP) {
		SERVERIP = sERVERIP;
	}
	public String getSERVERIP() {
		return SERVERIP;
	}
	public JFrame getFreemindMainFrame() {
		return freemindMainFrame;
	}

	public void setFreemindMainFrame(JFrame freemindMainFrame) {
		this.freemindMainFrame = freemindMainFrame;
	}

	public ProfileFrame getProfileFrame() {
		return profileFrame;
	}

	public void setProfileFrame(JFrame profileFrame) {
		this.profileFrame = (ProfileFrame) profileFrame;
	}

	public String getDownPath() {
		return downPath;
	}
	
	public int getPdfPage() {
		return pdfPage;
	}

	public void setPdfPage(int pdfPage) {
		this.pdfPage = pdfPage;
	}


	public MindMapMapModel getmModel() {
		return mModel;
	}

	public void setmModel(MindMapMapModel mModel) {
		this.mModel = mModel;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public OutputStream getOs() {
		return os;
	}
	
	public void setOs(OutputStream os) {
		this.os = os;
	}
	
	public InputStream getIn() {
		return in;
	}
	
	public void setIn(InputStream in) {
		this.in = in;
	}
	
	public Controller getC() {
		return c;
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	public MindMapController getMc() {
		return mc;
	}
	
	public void setMc(MindMapController mc) {
		this.mc = mc;
	}
	public SlideShow getSlideShow() {
		return slideShow;
	}
	
	public void setSlideShow(SlideShow slideShow) {
		this.slideShow = slideShow;
	}
	public boolean isSlideShowInfo() {
		return isSlideShowInfo;
	}
	
	public void setSlideShowInfo(boolean isSlideShowInfo) {
		this.isSlideShowInfo = isSlideShowInfo;
	}
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	
	
	
	
	
	
	
	
}
