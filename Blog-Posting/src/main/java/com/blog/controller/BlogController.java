package com.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Blog;
import com.blog.service.imple.BlogServiceImpl;

@RestController
@RequestMapping("/blogs")
public class BlogController 
{
	private BlogServiceImpl blogs;
	
	public BlogController(BlogServiceImpl blogService) {
		this.blogs = blogService;
	}
	
	@RequestMapping("/home")
	public String home()
	{
		return "Hello World";
	}
	
	// Adding a blog to a database & show the current saved blog...
	
	@PostMapping("/addBlog")
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog)
	{
		Blog savedBlog = blogs.saveBlog(blog);
		return ResponseEntity.ok(savedBlog);
	}
	
	//Getting All Blogs from the database
	
	@GetMapping("/readAllBlogs")
	public List<Blog> readBlogs(@RequestBody Blog blog)
	{
		return blogs.getAllBlogs(blog);
	}
}
