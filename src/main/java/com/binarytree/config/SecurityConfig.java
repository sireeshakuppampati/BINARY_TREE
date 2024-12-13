package com.binarytree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/tree_nodes/**").permitAll() // Allow public access to home and API routes
                        .anyRequest().authenticated() // Require authentication for all other routes
                )
                .httpBasic(); // Use basic HTTP authentication (can be replaced with JWT or OAuth2)

        return http.build();
    }
}
