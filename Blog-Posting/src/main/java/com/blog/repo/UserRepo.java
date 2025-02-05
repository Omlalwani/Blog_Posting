package com.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
	User findByEmail(String email);
}
