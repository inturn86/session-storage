package com.redis.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/hello")
	public String helloWorld(@RequestParam String userId) {
//		System.out.println("test");`111
		return "Hello World" + userId;
	}
}
