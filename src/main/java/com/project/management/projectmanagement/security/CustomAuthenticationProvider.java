package com.project.management.projectmanagement.security;

import com.project.management.projectmanagement.entity.ProjectUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private CustomUserDetailsService userDetailsService;
    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService) {
        userDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Need to get details for user authentication");
        // Check whether the user exist in the database or not
        ProjectUser user = userDetailsService.loadUserByUsername(authentication.getName());
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
