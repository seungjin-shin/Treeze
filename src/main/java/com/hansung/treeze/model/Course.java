package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Course extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4685366043349125403L;
	private Long lectureId;
	private String lectureName;
	private String studentEmail;
	
	public Long getLectureId() {
		return lectureId;
	}
	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}


}
