package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entities.Blog;

@Service
public interface BlogService 
{
	Blog saveBlog(Blog blog);
	List<Blog> getAllBlogs(Blog blog);
}
