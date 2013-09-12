package com.hansung.treeze.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class NaviInfo extends AbstractPersistable<Long>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1222007616443185560L;
	
	private Long classId;
	String nodeID;

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}
}

