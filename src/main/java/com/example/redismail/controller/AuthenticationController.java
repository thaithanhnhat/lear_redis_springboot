package com.example.redismail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redismail.dto.request.OtpRegisterRequest;
import com.example.redismail.entity.User;
import com.example.redismail.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/verifyotp")
	User createUser(@RequestBody OtpRegisterRequest request) {
		var result = authenticationService.createUser(request);
		
		return result;
	}
	
}
