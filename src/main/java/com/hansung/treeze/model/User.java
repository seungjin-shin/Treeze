package com.hansung.treeze.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User extends AbstractPersistable<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8670230733755158882L;
	public static final String STUDENT = "student";
	public static final String PROFESSOR = "professor";
	
	private int userImgId;  
	private String userType;
	private int studentIdentificatinNumber;
	private String userName;
	private String userEmail;
	private int password;
	
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserImgId() {
		return userImgId;
	}
	public void setUserImgId(int userImgId) {
		this.userImgId = userImgId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public int getStudentIdentificatinNumber() {
		return studentIdentificatinNumber;
	}
	public void setStudentIdentificatinNumber(int studentIdentificatinNumber) {
		this.studentIdentificatinNumber = studentIdentificatinNumber;
	}
	
}
