package freemind.controller;

import freemind.modes.mindmapmode.MindMapController;

public class FreemindManager {
	private static FreemindManager fInstance;
	
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
	


	private boolean isSlideShowInfo = false;
	private boolean isAddQuestionNodeInfo = false;


	private String ticketTitle;
	private String ticketContent;
	private String ticketWriter;
	
	private String filePath;
	
	
	
	
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
