package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Lecture extends AbstractPersistable<Long>  {



	/**
	 * 
	 */
	private static final long serialVersionUID = 9125643675879533721L;
	private String professorEmail;
	private String lectureName;
	private Long lectureId;
	private Boolean stateOfLecture;
	private String professorName;
	
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
	public Long getLectureId() {
		return lectureId;
	}
	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
}
