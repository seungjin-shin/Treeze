package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Mindmap extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223917314206748623L;
	private String mindmapPath;
	private int ptId;
	
	public Mindmap(){
		
	}
	
	public Mindmap(String mindmapPath, int ptId){
		this.mindmapPath = mindmapPath;
		this.ptId = ptId;
	}
	
	public String getMindmapPath() {
		return mindmapPath;
	}
	public void setMindmapPath(String mindmapPath) {
		this.mindmapPath = mindmapPath;
	}
	public int getPtId() {
		return ptId;
	}
	public void setPtId(int ptId) {
		this.ptId = ptId;
	}
}
