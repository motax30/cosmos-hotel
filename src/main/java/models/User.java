package models;

public class User {
	// Login Information
	private String userName;
	private String email;
	private transient String password;
	
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
}
