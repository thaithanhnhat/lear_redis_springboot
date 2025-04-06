package com.example.redismail.dto.request;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserRequest {

	private String name;
	private String mail;
	private String password;
	
	
	public UserRequest(String name, String mail, String password) {

		this.name = name;
		this.mail = mail;
		this.password = password;
	}
	public UserRequest() {

	}
	
    

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
