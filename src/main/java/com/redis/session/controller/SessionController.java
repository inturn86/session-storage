package com.redis.session.controller;

import com.redis.session.define.RoleConst;
import com.redis.session.define.SessionConst;
import com.redis.session.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class SessionController {
	@PostMapping("/user/login")
	public String login(@RequestBody User user, HttpSession session) {
		session.setAttribute(SessionConst.LOGIN_USER, user);
		return session.getId();
	}

	@PostMapping("/user/logout")
	public String logout(HttpSession session) {
		log.error("login ");
		session.removeAttribute(SessionConst.LOGIN_USER);
		return session.getId();
	}

	@GetMapping("/user/name")
	public String getName(HttpSession session) {

		User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
		return user.email();
	}

	@Secured(RoleConst.ROLE_WORKER)
	@GetMapping("/user/id")
	public String getMaster(HttpSession session) {
		User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
		return user.userId();
	}

	@Secured(RoleConst.ROLE_ADMIN)
	@GetMapping("/user/password")
	public String getNormal(HttpSession session) {
		User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
		return user.password();
	}
}
