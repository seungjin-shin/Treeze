package com.example.adroidmind.data;

import android.R.string;
import android.text.Editable;

public class Ticket extends MindNode {
	private int classId;
	private String position;
	private String contents;
	private int ticketNumber;
	private String userName;
	private String ticketTitle;
	

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(MindNode parent, String title, String contents,String userName) {
		// TODO Auto-generated constructor stub
		this.nodeStr = title;
		this.ticketTitle = title;
		this.ticketNumber = -1;
		this.contents = contents;
		this.parentNode = parent;
		this.userName = userName;
		if (!parent.isExistTicket()) {
			parent.setExistTicket(true);
			parent.setScaleX(parent.getScaleX() + 60);
			parent.setendX();
		}
		
		parentNode.childeNodes.add(this);
		this.absoluteIndex = parent.ChildCount;
		parent.ChildCount++;
		if (parent.equals(parent.getRoot())) {
			position = (parent.childeNodes.size() - 1) + "";
		} else {
			position = parent.getPosition() + "/"
					+ (parent.childeNodes.size() - 1);
		}
		
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
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
