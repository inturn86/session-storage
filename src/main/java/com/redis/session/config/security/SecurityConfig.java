package com.redis.session.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	//	기존 WebSecurityConfigurerAdapter를 상속 후에
	//, configure 메소드를 오버라이딩 하는 방식은 deprecated 됐다.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(r ->
						r.requestMatchers( "/user/join", "/user/login").permitAll()
								.anyRequest().authenticated()
				)
				.formLogin(formLogin -> formLogin.disable())
				.csrf(csrf -> csrf.disable())
				.sessionManagement(sessionManagement ->
						//무상태 계층으로 유지?
						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(handle ->
						handle.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
								)
				.addFilterBefore(new UserAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
