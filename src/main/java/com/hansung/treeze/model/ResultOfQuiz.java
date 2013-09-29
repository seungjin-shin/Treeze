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
	private String nodeId;
	private String quizId;
	private String type;
	private String userEmail;
	
	public static String DESCRIPTIVE = "descriptive";
	public static String MULTIPLECHOICE = "multipleChoice";
	
	@Column(columnDefinition="LONGTEXT") 
	private String answerContents;
	private Boolean answerNumber1;
	private Boolean answerNumber2;
	private Boolean answerNumber3;
	private Boolean answerNumber4;
	private Boolean answerNumber5;
	
	
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getAnswerNumber1() {
		return answerNumber1;
	}
	public void setAnswerNumber1(Boolean answerNumber1) {
		this.answerNumber1 = answerNumber1;
	}
	public Boolean getAnswerNumber2() {
		return answerNumber2;
	}
	public void setAnswerNumber2(Boolean answerNumber2) {
		this.answerNumber2 = answerNumber2;
	}
	public Boolean getAnswerNumber3() {
		return answerNumber3;
	}
	public void setAnswerNumber3(Boolean answerNumber3) {
		this.answerNumber3 = answerNumber3;
	}
	public Boolean getAnswerNumber4() {
		return answerNumber4;
	}
	public void setAnswerNumber4(Boolean answerNumber4) {
		this.answerNumber4 = answerNumber4;
	}
	public Boolean getAnswerNumber5() {
		return answerNumber5;
	}
	public void setAnswerNumber5(Boolean answerNumber5) {
		this.answerNumber5 = answerNumber5;
	}
	
	
}
