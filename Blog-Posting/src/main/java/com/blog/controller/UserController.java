package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.User;
import com.blog.service.JwtService;
import com.blog.service.imple.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserServiceImpl users;
	
	@Autowired
	JwtService jwtService;

	
	
	@RequestMapping("/check")
	public String check()
	{
		return "Hey!, Windows";
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> addUser(@RequestBody User user)
	{
		User savedUser = users.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}
	
	
	
	@GetMapping("/loginUser")
	public String login(@RequestParam String email, @RequestParam String password)
	{
		User user = new User();
		String isUser = users.loginUser(email, password);
		
		if(isUser != null)
		{
			System.out.println("Email: " + email);
			String token = jwtService.generateToken(email);
			return "Login Successfull : " + " Email : " + email + " : "+ token;
			
		}else
		{
			return "Invalid Email or Password, Try Again!";
		}
	}
}
