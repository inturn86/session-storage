package com.redis.session.config.security;

import com.redis.session.define.SessionConst;
import com.redis.session.domain.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class UserAuthenticationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(SessionConst.LOGIN_USER);
		if(!Objects.isNull(user)) {
			GrantedAuthority authority = new SimpleGrantedAuthority(user.role()); // 사용자 권한
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.email(), user.email(), Collections.singleton(authority)); // 현재 사용자의 인증 정보
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request,response);
	}
}
