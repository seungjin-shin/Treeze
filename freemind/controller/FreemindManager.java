package freemind.controller;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import freemind.main.ProfileFrame;
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
	}
	
	SlideShow slideShow;
	MindMapController mc;
	Controller c;

	OutputStream os;
	InputStream in;

	private boolean isSlideShowInfo = false;
	private boolean isAddQuestionNodeInfo = false;


	private String ticketTitle;
	private String ticketContent;
	private String ticketWriter;
	
	private String filePath;// = "/Users/dewlit/Desktop/test/Linux/";
	private String downPath = System.getProperty("user.home") + System.getProperty("file.separator") + "Treeze";

	private int classId = 1;
	private int pdfPage;
	private MindMapMapModel mModel;
	private ProfileFrame profileFrame;
	private JFrame freemindMainFrame;
	
	public Color treezeColor = new Color(141, 198, 63);
	
	//(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));
	public Image treezeLogo = new ImageIcon(getClass().getClassLoader().getResource("images/treezeLogo.png")).getImage();
	public Image loginInputBar = new ImageIcon(getClass().getClassLoader().getResource("images/LoginInputBar.png")).getImage();
	public Image login = new ImageIcon(getClass().getClassLoader().getResource("images/login.png")).getImage();
	public Image professorImg = new ImageIcon(getClass().getClassLoader().getResource("images/minsuk.jpg")).getImage();
	public Image addLecture = new ImageIcon(getClass().getClassLoader().getResource("images/addlecture1.png")).getImage();
	public Image deleteLecture = new ImageIcon(getClass().getClassLoader().getResource("images/deletelecture1.png")).getImage();
	public Image lectureList = new ImageIcon(getClass().getClassLoader().getResource("images/lecturelist1.png")).getImage();
	public Image addClass = new ImageIcon(getClass().getClassLoader().getResource("images/addclass1.png")).getImage();
	public Image deleteClass = new ImageIcon(getClass().getClassLoader().getResource("images/deleteclass1.png")).getImage();
	
	
	
	
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
	public boolean isAddQuestionNodeInfo() {
		return isAddQuestionNodeInfo;
	}
	
	public void setAddQuestionNodeInfo(boolean isAddQuestionNodeInfo) {
		this.isAddQuestionNodeInfo = isAddQuestionNodeInfo;
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

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public String getTicketContent() {
		return ticketContent;
	}

	public void setTicketContent(String ticketContent) {
		this.ticketContent = ticketContent;
	}

	public String getTicketWriter() {
		return ticketWriter;
	}

	public void setTicketWriter(String ticketWriter) {
		this.ticketWriter = ticketWriter;
	}

	
	
	
	
	
	
	
	
}
