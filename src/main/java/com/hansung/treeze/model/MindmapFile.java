package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class MindmapFile extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223917314206748623L;

	private int ptId;
	private String lectureName;
	private String fileName;
	private String filePath;
	private String fileSize;
	private String fileDownloadUrl;
	
	public MindmapFile(){
		
	}


	public int getPtId() {
		return ptId;
	}
	public void setPtId(int ptId) {
		this.ptId = ptId;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileDownloadUrl() {
		return fileDownloadUrl;
	}
	public void setFileDownloadUrl(String fileDownloadUrl) {
		this.fileDownloadUrl = fileDownloadUrl;
	}
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

}
