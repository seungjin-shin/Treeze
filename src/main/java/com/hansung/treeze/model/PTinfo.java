package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PTinfo  extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 336076262914199360L;

	private int ptId;
	private int imgCnt;
	private String nodeName;
	
	public PTinfo() {
	}
	
	public PTinfo(int ptId, int imgCnt, String nodeName) {
		this.ptId = ptId;
		this.imgCnt = imgCnt;
		this.nodeName = nodeName;
	}
	
	
	public int getPtId() {
		return ptId;
	}
	public void setPtId(int ptId) {
		this.ptId = ptId;
	}
	public int getImgCnt() {
		return imgCnt;
	}
	public void setImgCnt(int imgCnt) {
		this.imgCnt = imgCnt;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
}
