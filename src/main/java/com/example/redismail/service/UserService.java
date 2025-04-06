package com.example.redismail.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redismail.dto.request.ResendOtpRequest;
import com.example.redismail.dto.request.UserRequest;
import com.example.redismail.entity.User;
import com.example.redismail.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	@Qualifier("redisTemplateUserRequest")
	private RedisTemplate<String, UserRequest> raRedisTemplate;
	
	@Autowired
	@Qualifier("redisTemplateString")
	private RedisTemplate<String, String> raRedisTemplateString;
	
	public boolean register(UserRequest request) {
		if(userRepository.existsByMail(request.getMail()))
			throw new RuntimeException("This gmail used!");
		raRedisTemplate.opsForValue().set("register:" + request.getMail(), request,5,TimeUnit.MINUTES);
		
		return mailService.sendOtpEmail(request.getMail());
	}	
	public boolean resendOtp(ResendOtpRequest request) {
		UserRequest userRequest = raRedisTemplate.opsForValue().get("register:" +request.getMail());
		
		String pattern = "otp:" + request.getMail() + "*";
		Set<String> keys = raRedisTemplateString.keys(pattern); 
		if(keys != null && !keys.isEmpty())
			throw new RuntimeException("Please wait 60 second!");
		
		if(userRequest == null)
			throw new RuntimeException("You are not registerd!");
		
		return mailService.sendOtpEmail(userRequest.getMail());
	}
}
