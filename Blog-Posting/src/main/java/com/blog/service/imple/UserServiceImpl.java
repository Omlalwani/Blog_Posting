package com.blog.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.repo.UserRepo;
import com.blog.service.JwtService;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtService jwtService;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public String loginUser(String email, String password) {
		/*
		 * User user = new User();
		 * 
		 * Authentication authentication = authManager.authenticate(new
		 * UsernamePasswordAuthenticationToken(email, password));
		 * if(authentication.isAuthenticated()) return
		 * jwtService.generateToken(user.getUsername());
		 * 
		 * return "fail";
		 */
		
		
		  
		  
		  User user = userRepo.findByEmail(email); String encodedPassword =
		  user.getPassword();
		  
		  if(passwordEncoder.matches(password, encodedPassword)) { return "Successfull"; }
		  return null;
		 
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByEmail(email);
		if(user!=null)
		{
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRole().toString())
					.build();
		}
		throw new UsernameNotFoundException("User not found!...." + email);
	}

	
	  
	 
	
	
}
