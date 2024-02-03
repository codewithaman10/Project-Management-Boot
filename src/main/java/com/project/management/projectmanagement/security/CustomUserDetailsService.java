package com.project.management.projectmanagement.security;

import com.project.management.projectmanagement.dao.MyUserDao;
import com.project.management.projectmanagement.entity.MyUser;
import com.project.management.projectmanagement.entity.ProjectUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private MyUserDao myUserDao;

    @Override
    public ProjectUser loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Fetching details for user: {}", username);
        Optional<MyUser> user = myUserDao.findByUserName(username);

        logger.info("User details in DB: {}", user.orElse(null));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No such user exist in database." + username);
        }

        return ProjectUser.builder()
                .username(user.get().getUserName().toLowerCase())
                .password(user.get().getPassword())
                .isEnabled(user.get().isEnabled())
                .email(user.get().getEmailId())
                .roles(extractAuthorities(user.get().getRoles()))
                .build();
    }

    private List<? extends GrantedAuthority> extractAuthorities(String roles) {
        return Arrays.stream(roles.split(",")).map(
                SimpleGrantedAuthority::new
        ).toList();
    }
}
