package com.treeze.data;


public class User  {
	private  static User user;
	public static final String STUDENT = "student";
	public static final String PROFESSOR = "professor";
	 
	private int userImgId;  
	private String userType;
	private String identificationNumber;
	private String userName;
	private String userEmail;
	private String password;
	private User(){}
	public static User getInstance() {
		if(user ==null){
			user =new User();
		}
		return user;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
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
	
}
