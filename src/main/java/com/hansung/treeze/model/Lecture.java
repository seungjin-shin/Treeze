package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Lecture extends AbstractPersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6401514770595701938L;

	private String professorEmail;
	private String lectureName;
	private Boolean stateOfLecture;
	
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
	public Boolean getStateOfLecture() {
		return stateOfLecture;
	}
	public void setStateOfLecture(Boolean stateOfLecture) {
		this.stateOfLecture = stateOfLecture;
	}
}
