package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Ticket extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7428221965877371943L;

	private Long classId;
	private String position;
	private String contents;
	private String userName;
	private String ticketTitle;
	
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
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTicketTitle() {
		return ticketTitle;
	}
	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}
}
