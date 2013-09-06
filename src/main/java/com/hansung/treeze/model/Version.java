package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Version extends AbstractPersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8027579637309033719L;
	
	public static final String STUDENT = "student";
	public static final String PROFESSOR = "professor";
	String userType;
	String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
