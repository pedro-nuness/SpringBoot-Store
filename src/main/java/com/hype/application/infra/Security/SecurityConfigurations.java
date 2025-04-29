package com.hype.application.infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;


    private static final String[] AUTH_WHITELIST = {
            "/auth/**"
    };

    private static final String[] PUBLIC_GET_RESOURCES = {
            "/files/**"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/product/**",
            "/category/**",
            "/product_type/**",
            "/collection/**"
    };
    private static final String[] ADMIN_GET_ENDPOINTS = {

    };

    private static final String[] PUBLIC_GET_DATA = {
            "/product/**",
            "/category/**",
            "/product_type/**",
            "/collection/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //Browser
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()

                        // Auth
                        .requestMatchers(HttpMethod.POST, AUTH_WHITELIST).permitAll()

                        // Public resources
                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_RESOURCES).permitAll()

                        // Public access
                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_DATA).permitAll()

                        // Admin-only endpoints
                        .requestMatchers(HttpMethod.GET, ADMIN_GET_ENDPOINTS).hasAnyAuthority("ROLE_ADMIN", "ROLE_MASTER")
                        .requestMatchers(HttpMethod.POST, ADMIN_ENDPOINTS).hasAnyAuthority("ROLE_ADMIN", "ROLE_MASTER")
                        .requestMatchers(HttpMethod.PUT, ADMIN_ENDPOINTS).hasAnyAuthority("ROLE_ADMIN", "ROLE_MASTER")
                        .requestMatchers(HttpMethod.DELETE, ADMIN_ENDPOINTS).hasAnyAuthority("ROLE_ADMIN", "ROLE_MASTER")

                        // All other requests
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
