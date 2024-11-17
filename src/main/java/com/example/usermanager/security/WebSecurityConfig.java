package com.example.usermanager.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/",
                                "/index",
                                "/users",
                                "/users/new",
                                "/users/details/**",
                                "/users/edit/**",
                                "/users/delete/**",
                                "/user-form",
                                "/user-list")
                        .permitAll()
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/scss/**")
                        .permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
