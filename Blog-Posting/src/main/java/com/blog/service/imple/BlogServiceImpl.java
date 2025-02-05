package com.blog.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Blog;
import com.blog.repo.BlogRepo;
import com.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService
{
	@Autowired
	BlogRepo blogRepo;

	@Override
	public Blog saveBlog(Blog blog) {
		return blogRepo.save(blog);
	}

	@Override
	public List<Blog> getAllBlogs(Blog blog) {
		// TODO Auto-generated method stub
		return blogRepo.findAll();
	}

}
