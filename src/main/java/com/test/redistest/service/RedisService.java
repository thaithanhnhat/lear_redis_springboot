package com.test.redistest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // Lưu trữ dữ liệu kiểu String
    public void saveStringData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // Lấy dữ liệu kiểu String
    public String getStringData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
