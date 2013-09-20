package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Ticket extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7428221965877371943L;

	private Long ticketId;
	private Long classId;
	private String parentNodeId;
	private String contents;
	private String userName;
	private String userEmail;
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public String getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
