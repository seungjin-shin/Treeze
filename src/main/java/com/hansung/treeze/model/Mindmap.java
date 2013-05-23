package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Mindmap extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223917314206748623L;
	
	private String mindmapName;
	private int classId;
	private String mindmapXML;
	
	public Mindmap(){
		
	}
	
	public Mindmap(String mindmapXML){
		this.mindmapXML = mindmapXML;
	}
	
	public String getmindmapXML() {
		return mindmapXML;
	}
	public void setmindmap(String mindmapXML) {
		this.mindmapXML = mindmapXML;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getMindmapName() {
		return mindmapName;
	}

	public void setMindmapName(String mindmapName) {
		this.mindmapName = mindmapName;
	}

}
