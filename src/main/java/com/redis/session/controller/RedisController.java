package com.redis.session.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	@GetMapping("/api/session/{userId}")
	public String getSessionId(@PathVariable String userId, HttpSession session) {
		session.setAttribute("name", userId);
		return session.getId();
	}
}
