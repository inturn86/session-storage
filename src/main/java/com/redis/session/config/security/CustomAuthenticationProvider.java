package com.redis.session.config.security;

import com.redis.session.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

//        User memberContext = (User) userDetailsService.loadUserByUsername(username);

        User user = User.builder().userId("test").email("test@naver.com").password("tt").role("ROLE_USER").build();
//        if (!passwordEncoder.matches(password, memberContext.getPassword())) {
//            throw new BadCredentialsException(DO_NOT_MATCHES_PSWD);
//        }
        return new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(user.role())));
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
