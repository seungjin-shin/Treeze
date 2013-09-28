package com.hansung.treeze.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Quiz extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472524997776849776L;
	private Long classId;
	private String nodeId;
	private String quizId;
	private String type;
	private String time;
	
	public static String DESCRIPTIVE = "descriptive";
	public static String MULTIPLECHOICE = "multipleChoice";

	@Column(columnDefinition="LONGTEXT") 
	private String contents;
	
	//descriptive
	private String answerContents;
	private Integer answerNumber;
	
	//multipleChoice
	private String example1;
	private String example2;
	private String example3;
	private String example4;
	private String example5;
	
	
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public String getExample1() {
		return example1;
	}
	public void setExample1(String example1) {
		this.example1 = example1;
	}
	public String getExample2() {
		return example2;
	}
	public void setExample2(String example2) {
		this.example2 = example2;
	}
	public String getExample3() {
		return example3;
	}
	public void setExample3(String example3) {
		this.example3 = example3;
	}
	public String getExample4() {
		return example4;
	}
	public void setExample4(String example4) {
		this.example4 = example4;
	}
	public String getExample5() {
		return example5;
	}
	public void setExample5(String example5) {
		this.example5 = example5;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	
	
	

}
