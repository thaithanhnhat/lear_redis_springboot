package com.example.redismail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redismail.dto.request.ResendOtpRequest;
import com.example.redismail.dto.request.UserRequest;
import com.example.redismail.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
   @Autowired
   private UserService userService;
   	
   @PostMapping
   boolean register(@RequestBody UserRequest request) {
	   return userService.register(request);
   }
   @PostMapping("resendotp")
   boolean resendotp(@RequestBody ResendOtpRequest request) {
	   return userService.resendOtp(request);
   }
   

}
