package com.redis.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//	기존 WebSecurityConfigurerAdapter를 상속 후에
	//, configure 메소드를 오버라이딩 하는 방식은 deprecated 됐다.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//이게 뭘까?
		//cors crsf 이런거 알아보자.
		http.authorizeHttpRequests((r) -> r.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
