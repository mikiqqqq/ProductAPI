package com.example.product_api;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String email = request.getHeader("Email");
        String hashedPassword = request.getHeader("Hashed-Password");

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Compare the hashedPassword from the request with the one stored in the database
            if (hashedPassword.equals(userDetails.getPassword())) {
                // Set authorities for the authenticated user based on roles retrieved from the database
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Authenticated user: " + userDetails.getUsername());
                userDetails.getAuthorities().forEach(authority -> System.out.println("Role: " + authority.getAuthority()));
            } else {
                // Authentication failed
                throw new UsernameNotFoundException("Invalid credentials");
            }
        } catch (UsernameNotFoundException e) {
            // User not found
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response); // Proceed with the filter chain
    }
}