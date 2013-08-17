package freemind.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.JFrame;

import freemind.modes.mindmapmode.MindMapController;
import freemind.modes.mindmapmode.MindMapMapModel;

public class FreemindManager {
	private static FreemindManager fInstance;
	public static final String SERVERIP = "113.198.84.74"; 
	
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
//	private String serverIP = "113.198.84.74";
	private String serverIP = "14.63.215.88";
	private String downPath = System.getProperty("user.home") + "/Treeze";

	private int classId = 1;
	private int pdfPage;
	private MindMapMapModel mModel;
	private JFrame profileFrame;
	
	
	
	public JFrame getProfileFrame() {
		return profileFrame;
	}

	public void setProfileFrame(JFrame profileFrame) {
		this.profileFrame = profileFrame;
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

	public String getServerIP() {
		return serverIP;
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
