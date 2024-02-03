package com.project.management.projectmanagement.service;

import com.project.management.projectmanagement.dao.MyUserDao;
import com.project.management.projectmanagement.dto.security.AuthResponse;
import com.project.management.projectmanagement.dto.security.AuthenticationRequest;
import com.project.management.projectmanagement.dto.security.RegisterRequest;
import com.project.management.projectmanagement.entity.MyUser;
import com.project.management.projectmanagement.entity.ProjectUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    private final MyUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(MyUserDao userDao, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        MyUser user = userDao.save(MyUser.builder()
                .userName(registerRequest.getUsername().toLowerCase())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .emailId(registerRequest.getEmail())
                .roles("ROLE_USER")
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build());
        String jwtToken = jwtService.generateToken(
                ProjectUser.builder()
                        .username(user.getUserName().toLowerCase())
                        .password(user.getPassword())
                        .build()
        );

        return new AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername().toLowerCase(), authenticationRequest.getPassword())
        );

        // If the above method is successfully executed it means that the user is authenticated they are who they are claiming
        MyUser user = userDao.findByUserName(authenticationRequest.getUsername()).orElseThrow(() -> new Exception("No such user exception."));
        String jwtToken = jwtService.generateToken(
                ProjectUser.builder()
                        .username(user.getUserName().toLowerCase())
                        .password(user.getPassword())
                        .build()
        );

        return new AuthResponse(jwtToken);
    }
}
