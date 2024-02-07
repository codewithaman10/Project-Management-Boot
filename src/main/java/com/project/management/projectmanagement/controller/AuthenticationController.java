package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.dto.security.AuthResponse;
import com.project.management.projectmanagement.dto.security.AuthenticationRequest;
import com.project.management.projectmanagement.dto.security.RegisterRequest;
import com.project.management.projectmanagement.service.AuthenticationService;
import com.project.management.projectmanagement.util.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    // Authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/get-user")
    public ResponseEntity<String> getCurrentLoggedInUser() {
        return ResponseEntity.ok(SecurityContextUtil.getUsername());
    }
}
