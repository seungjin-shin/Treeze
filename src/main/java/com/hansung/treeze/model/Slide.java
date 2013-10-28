package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Slide extends AbstractPersistable<Long> {
	/**
	 * 클래스-1:n-슬라이드
	 */
	private Long classId;

	/**
	 * 페이지 번호
	 */
	private int page;

	/**
	 * 슬라이드 이미지 파일명
	 */
	private String filename;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private static final long serialVersionUID = -7191936211293946064L;
}
