package com.example.redismail.service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.redismail.dto.request.UserRequest;
import com.example.redismail.entity.User;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    @Qualifier("redisTemplateString")
    private RedisTemplate<String, String> redisTemplate;
    
    public boolean sendOtpEmail(String to) {
        try {
            // Tạo và gửi email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Your OTP Code");
            String otp = generateOtp();
            message.setText("Your OTP code is: " + otp);

            javaMailSender.send(message);
            
            //thêm OTP_KEY_PREFIX để quản lý task riêng biệt
            redisTemplate.opsForValue().set("otp:"+to+":"+otp, to, 60, TimeUnit.SECONDS);
            
            return true;  // Trả về true nếu gửi email thành công
        } catch (Exception e) {
            // Nếu có lỗi trong quá trình gửi email
            return false; // Trả về false nếu có lỗi
        }
    }

    public static String generateOtp() {
    	String OTP_CHARACTERS = "0123456789";
    	int OTP_LENGTH = 6;
    	
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(OTP_CHARACTERS.charAt(random.nextInt(OTP_CHARACTERS.length())));
        }

        return otp.toString();
    }
}