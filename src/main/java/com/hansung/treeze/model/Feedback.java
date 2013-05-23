package com.hansung.treeze.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class Feedback extends AbstractPersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7454319885796951545L;

	private int classId;
	private String position;
	private String contents;
	private String userEmail;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
