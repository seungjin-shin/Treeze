package freemind.controller;

public class FreemindManager {
	private static FreemindManager fInstance;
	
	public static FreemindManager getInstance(){
		if(fInstance == null){
			fInstance = new FreemindManager();
		}
		return fInstance;
	}
	
	private boolean isQuestion = false;
	private boolean addQuestionNode = false;
	private boolean isSlideShowInfo = false;
	private boolean isAddQuestionNodeInfo = false;


	private String ticketTitle;
	private String ticketContent;
	private String ticketWriter;
	
	private String filePath;
	
	
	
	
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

	public boolean isAddQuestionNode() {
		return addQuestionNode;
	}

	public void setAddQuestionNode(boolean addQuestionNode) {
		this.addQuestionNode = addQuestionNode;
	}

	public boolean isQuestion() {
		return isQuestion;
	}

	public void setQuestion(boolean isQuestion) {
		this.isQuestion = isQuestion;
	}
	
	
	
	
	
	
	
	
}
