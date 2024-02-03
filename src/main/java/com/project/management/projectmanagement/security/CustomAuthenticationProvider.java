package com.project.management.projectmanagement.security;

import com.project.management.projectmanagement.entity.ProjectUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ProjectUser user = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        // Check the password shared by user with what is stored in the database
        if (Objects.isNull(authentication.getCredentials().toString()) ||
                // First param is the rawPassword and the second param is encodedPassword
                !passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Username/Password combination is wrong");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
