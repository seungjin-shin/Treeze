package com.hansung.treeze.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Mindmap extends AbstractPersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7894431277734556571L;
	private int classId; 
	//@Column(columnDefinition="LONGTEXT") 
	private String mindmapXML;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getMindmapXML() {
		return mindmapXML;
	}
	public void setMindmapXML(String mindmapXML) {
		this.mindmapXML = mindmapXML;
	}
}
