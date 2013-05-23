package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Mindmap extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223917314206748623L;
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

}
