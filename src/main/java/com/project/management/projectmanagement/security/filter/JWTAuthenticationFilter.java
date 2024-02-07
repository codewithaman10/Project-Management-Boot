package com.project.management.projectmanagement.security.filter;

import com.project.management.projectmanagement.entity.ProjectUser;
import com.project.management.projectmanagement.security.CustomUserDetailsService;
import com.project.management.projectmanagement.service.JwtService;
import com.project.management.projectmanagement.util.SecurityContextUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component  // To make it as a managed Bean
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Every filter in the Spring will have access to the HttpServletResponse and HttpServletRequest
     * even the custom ones that we create. Using which we extract the header and other information about the
     * request.
     * We can intercept every request and response coming in and going out of the application container, also like we can
     * alter the response to add Header
     * */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull  FilterChain filterChain) throws ServletException, IOException {
        log.info("Inside JWTAuthenticationFilter.");
        // Check whether we have the JWT token
        final String authHeader = request.getHeader("Authorization");

        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);    // Pass the request and response to next filter
            return;     // Since we don't want to continue with the execution of rest of this filter
        }

        // Extract the token from the authentication header
        final String jwt;
        final String username;
        jwt = authHeader.substring(7);  // Leave Bearer

        // Once the token is extracted we have to extract the user information from the token
        // and have to check if the user exist in our database
        username = jwtService.extractUsername(jwt);

        if (Objects.nonNull(username) &&
                Objects.isNull(SecurityContextUtil.getAuthentication()) // It will check if the user is not already authenticated
        ) {
            ProjectUser projectUser = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, projectUser)) {
                // If the token is valid then update the SecurityContext and send the request to our DispatcherServlet
                // We have to create an object of UsernamePasswordAuthenticationToken
                // This is needed by Spring and by the SecurityContextHolder in order to update the SecurityContext
                // UsernamePasswordAuthenticationFilter ??
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        projectUser,
                        null,   // Why null ??
                        projectUser.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Update the SecurityContextHolder
                SecurityContextUtil.setAuthentication(authToken);
            }

            // Call the rest of the filter chain
            filterChain.doFilter(request, response);
        }
    }
}
