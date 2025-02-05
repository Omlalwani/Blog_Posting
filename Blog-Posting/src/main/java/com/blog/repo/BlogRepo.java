package com.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entities.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer>
{
	
}
