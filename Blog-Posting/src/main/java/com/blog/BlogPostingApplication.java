package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.blog")
public class BlogPostingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPostingApplication.class, args);
	}
}