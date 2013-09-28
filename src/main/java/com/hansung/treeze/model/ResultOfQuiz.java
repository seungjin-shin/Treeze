package com.hansung.treeze.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ResultOfQuiz extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3286710162320984405L;
	private Long classId;
	private String quizId;
	
	private String userEmail;
	
	@Column(columnDefinition="LONGTEXT") 
	private String answerContents;
	private Integer answerNumber;
	
	
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getAnswerContents() {
		return answerContents;
	}
	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}
	public Integer getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
