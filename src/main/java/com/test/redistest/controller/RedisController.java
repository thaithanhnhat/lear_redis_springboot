package com.test.redistest.controller;


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

    // Endpoint để lưu dữ liệu vào Redis
    @PostMapping("/save")
    public String saveData(@RequestParam String key, @RequestParam String value) {
        redisService.saveStringData(key, value);
        return "Data saved to Redis!";
    }

    // Endpoint để lấy dữ liệu từ Redis
    @GetMapping("/get")
    public String getData(@RequestParam String key) {
        return redisService.getStringData(key);
    }
}
