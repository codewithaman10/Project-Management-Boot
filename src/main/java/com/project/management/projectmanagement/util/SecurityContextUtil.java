package com.project.management.projectmanagement.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

public class SecurityContextUtil {

    public static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public static Authentication getAuthentication() {
        return getContext().getAuthentication();
    }
    public static String getUsername() {
        return getAuthentication().getPrincipal().toString();
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthentication().getAuthorities();
    }

    public static void setAuthentication(Authentication authentication) {
        getContext().setAuthentication(authentication);
    }
}
