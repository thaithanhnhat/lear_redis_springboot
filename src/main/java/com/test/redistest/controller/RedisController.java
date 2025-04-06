package com.test.redistest.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.redistest.service.RedisService;

@RestController
public class RedisController {

	 @Autowired
	    private RedisService redisService;

	    @PostMapping("/save-user")
	    public String saveUserData(@RequestParam String email, @RequestParam String otp, @RequestParam boolean verified) {
	        redisService.saveUserVerification(email, otp, verified);
	        return "User data saved to Redis!";
	    }

	    @GetMapping("/get-user")
	    public Map<String, String> getUser(@RequestParam String email) {
	        return redisService.getUserData(email);
	    }
}
