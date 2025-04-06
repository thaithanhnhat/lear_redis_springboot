package com.example.redismail.dto.request;


public class OtpRegisterRequest {
	private String otp;
    private String mail;
    
    
	
	
	public OtpRegisterRequest(String otp, String mail) {
		this.otp = otp;
		this.mail = mail;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public OtpRegisterRequest() {
	}


	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
