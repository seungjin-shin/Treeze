package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class UploadedFile extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4009916462472998615L;
	private Long classId;
	private String fileName;
	private String filePath;
	private String fileSize;
	private String fileDownloadUrl;
	
	
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
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}

}