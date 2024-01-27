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

        return new ProjectUser(user.get().getUserName(), user.get().getPassword(), extractAuthorities(user.get().getRoles()), user.get().getEmailId());
    }

    private List<? extends GrantedAuthority> extractAuthorities(String roles) {
        return Collections.singletonList(new SimpleGrantedAuthority(roles));
    }
}
