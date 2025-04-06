package com.example.redismail.dto.request;

public class ResendOtpRequest {
	private String mail;
	
	public ResendOtpRequest(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
