package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ClassInfo extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3453484753339930178L;
	/**
	 * 
	 */
	
	private Long classId; /*������ �ٽ� �����ؾ��� */
	private String className;
	private String classIP;
	private int port;
	private Long lectureId;
	private String professorEmail;
	
	public String getProfessorEmail() {
		return professorEmail;
	}
	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}
	public String getClassIP() {
		return classIP;
	}
	public void setClassIP(String classIP) {
		this.classIP = classIP;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Long getLectureId() {
		return lectureId;
	}
	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}
}
