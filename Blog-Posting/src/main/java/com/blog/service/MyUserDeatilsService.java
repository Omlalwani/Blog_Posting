package com.blog.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.repo.UserRepo;

@Service
public class MyUserDeatilsService implements UserDetailsService 
{
	@Autowired
	UserRepo userRepo;
	
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		String roleset = user.getRole();
		
		
		String rolesArray = user.getRole();
				
		if(user == null)
			throw new UsernameNotFoundException("Username not found...." + email);
		return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(rolesArray)
				.build();
	}
	
}
