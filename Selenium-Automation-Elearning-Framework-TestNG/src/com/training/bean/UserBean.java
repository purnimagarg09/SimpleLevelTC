package com.training.bean;

public class UserBean {
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String login;
	private String password;
	private String profile;
	
	public UserBean() {
	}
	
	public UserBean(String firstname,String lastname,String email,String phone,String login,String password, String profile) {
		super();
		this.firstname = login;
		this.lastname = password;
		this.email = email;
		this.phone = phone;
		this.login = login;
		this.password = password;
		this.profile=profile;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String toString() {
		return "UserDetails [firstName=" + firstname + ", lastName=" + lastname + ", Email=" + email + ", Phone NUmber=" + phone + ", Login=" + login + ", Password=" + password + ", Profile=" + profile+"]";
	}

}
