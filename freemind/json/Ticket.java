package freemind.json;

import java.util.ArrayList;

public class Ticket{

	/**
	 * 
	 */
	private String ticketTitle;
	private int classId;
	private String position;
	private String ticketPosition;
	private String contents;
	private String userName;
	private ArrayList<Ticket> childList = new ArrayList<Ticket>();
	private boolean haveChild;
	
	public ArrayList<Ticket> getChildList() {
		return childList;
	}
	public void setChildList(ArrayList<Ticket> childList) {
		this.childList = childList;
	}
	public boolean isHaveChild() {
		return haveChild;
	}
	public void setHaveChild(boolean haveChild) {
		this.haveChild = haveChild;
	}
	public String getTicketPosition() {
		return ticketPosition;
	}
	public void setTicketPosition(String ticketPosition) {
		this.ticketPosition = ticketPosition;
	}
	public String getTicketTitle() {
		return ticketTitle;
	}
	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
}
