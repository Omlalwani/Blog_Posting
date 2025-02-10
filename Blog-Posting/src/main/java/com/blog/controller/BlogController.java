package com.blog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Blog;
import com.blog.entities.User;
import com.blog.repo.UserRepo;
import com.blog.service.imple.BlogServiceImpl;

@RestController
@RequestMapping("/blogs")
public class BlogController 
{
	@Autowired
	private BlogServiceImpl blogs;
	
	@Autowired
	UserRepo userRepo;
	
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
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog, Principal principal, @AuthenticationPrincipal UserDetails userDetails)
	{
		String email = principal.getName();
		User user = userRepo.findByEmail(email);
		
		blog.setUser(user);
		Blog savedBlog = blogs.saveBlog(blog);
		return ResponseEntity.ok(savedBlog);
	}
	
	//Getting All Blogs from the database
	
	@GetMapping("/readAllBlogs")
	public List<Blog> readBlogs(Blog blog)
	{
		return blogs.getAllBlogs(blog);
	}
}
