package com.example.appdevelopersblog.PhotoAppAPIUsers.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {

        return http.authorizeHttpRequests( requests -> requests
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
        )
                .csrf(csrf -> csrf
                        .disable()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .build();
    }

}
