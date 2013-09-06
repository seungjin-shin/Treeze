package com.hansung.treeze.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
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
	private int identificationNumber;
	private String userName;
	private String userEmail;
	private String password;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(int identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
}
