package com.hansung.treeze.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class Lecture extends AbstractPersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6401514770595701938L;

	private String professorEmail;
	private String lectureName;
	
	public String getProfessorEmail() {
		return professorEmail;
	}
	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
}
