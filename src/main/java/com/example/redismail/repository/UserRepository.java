package com.example.redismail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.redismail.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByMail(String mail);
}
