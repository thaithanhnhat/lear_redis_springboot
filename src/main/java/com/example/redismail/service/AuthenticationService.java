package com.example.redismail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redismail.dto.request.OtpRegisterRequest;
import com.example.redismail.dto.request.UserRequest;
import com.example.redismail.entity.User;
import com.example.redismail.repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("redisTemplateUserRequest")
	private RedisTemplate<String, UserRequest> redisTemplateUserRequest;
	
	@Autowired
	@Qualifier("redisTemplateString")
	private RedisTemplate<String, String> redisTemplateString;
	
	public User createUser(OtpRegisterRequest request) {
		String email = redisTemplateString.opsForValue().get(request.getOtp()+":"+request.getMail());
		if(email.isEmpty()){
			throw new RuntimeException("OTP invalid!");
		}
		
		UserRequest userRequest = redisTemplateUserRequest.opsForValue().get("register:" + email);
		
		//clean key when register success
		redisTemplateString.delete("otp:" + request.getOtp());
		redisTemplateUserRequest.delete("register:" + email);
		
		
	    User user = new User();
		user.setMail(userRequest.getMail());
		user.setName(userRequest.getName());
		user.setPassword(userRequest.getPassword());
		
		
	   return userRepository.save(user);
		
	}
	
	
}
