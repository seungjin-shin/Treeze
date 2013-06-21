package com.mindmap.adroidmind.data;

public class TicketInfo {
	private String position;//parents
	private String ticketPosition;
	private String ticketTitle;
	private String contents;
	private String userName;
	public TicketInfo(String ticketTitle,String contents,String ticketPosition,String position,String userName) {
		// TODO Auto-generated constructor stub
		this.ticketTitle = ticketTitle;
		this.contents = contents;
		this.position = position;
		this.userName = userName;
		this.ticketPosition = ticketPosition;
	}
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
