package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Timer extends AbstractPersistable<Long>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1855208933290821244L;
	private Long classId;
	private Boolean start = false;
	private String endTime;
	
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Boolean isStart() {
		return start;
	}
	public void setStart(Boolean start) {
		this.start = start;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
}
