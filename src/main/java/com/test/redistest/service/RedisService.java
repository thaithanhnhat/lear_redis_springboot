package com.test.redistest.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // redis hash
    public void saveUserVerification(String email, String otp, boolean verified) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        String redisKey = "user:" + email;
        hashOps.put(redisKey, "verified", String.valueOf(verified));
        hashOps.put(redisKey, "otp", otp);
        
        //expire 
        redisTemplate.expire(redisKey, 30, TimeUnit.SECONDS);
        
    }

    public Map<String, String> getUserData(String email) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        String redisKey = "user:" + email;
        return hashOps.entries(redisKey);
    }
    
    
    //redis List
    public void saveTask(String task) {
        redisTemplate.opsForList().leftPush("tasks", task);
    }
   
    public List<String> getTasks() {
        return redisTemplate.opsForList().range("tasks", 0, -1);
    }
    
    
    
    //redis Set
    public void addUser(String username) {
        redisTemplate.opsForSet().add("users", username);
    }

    public Set<String> getUsers() {
        return redisTemplate.opsForSet().members("users");
    }
}
