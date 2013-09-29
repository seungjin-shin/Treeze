package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ResultOfMultipleChoiceQuiz extends AbstractPersistable<Long> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1479439216138319580L;
	private Long classId;
	private String quizId;
	
	private Integer countOfexample1 = new Integer(0);
	private Integer countOfexample2 = new Integer(0);
	private Integer countOfexample3 = new Integer(0);
	private Integer countOfexample4 = new Integer(0);
	private Integer countOfexample5 = new Integer(0);
	
	

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

	public Integer getCountOfexample1() {
		return countOfexample1;
	}

	public void setCountOfexample1(Integer countOfexample1) {
		this.countOfexample1 = countOfexample1;
	}
	public Integer getCountOfexample2() {
		return countOfexample2;
	}
	public void setCountOfexample2(Integer countOfexample2) {
		this.countOfexample2 = countOfexample2;
	}
	public Integer getCountOfexample3() {
		return countOfexample3;
	}
	public void setCountOfexample3(Integer countOfexample3) {
		this.countOfexample3 = countOfexample3;
	}
	public Integer getCountOfexample4() {
		return countOfexample4;
	}
	public void setCountOfexample4(Integer countOfexample4) {
		this.countOfexample4 = countOfexample4;
	}
	public Integer getCountOfexample5() {
		return countOfexample5;
	}
	public void setCountOfexample5(Integer countOfexample5) {
		this.countOfexample5 = countOfexample5;
	}
	
	
}
