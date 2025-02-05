package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.entities.User;

@Service
public interface UserService 
{
	User saveUser(User user);
	String loginUser(String email, String password);
}
