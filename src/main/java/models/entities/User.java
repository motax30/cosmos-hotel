package models.entities;

import models.enumerates.UserType;

public class User {
	// Login Information
	private String userName;
	private String email;
	private transient String password;
	private UserType userType;
	
	// User Information
	private String firstName;
	private String lastName;
	
	/* 
	 * Constructors
	 */
	public User() {}
	
	public User(String userName) {
		super();
		this.userName = userName;
	}
	
	/*
	 * Get and Setters	
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
