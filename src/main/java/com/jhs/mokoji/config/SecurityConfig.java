package com.jhs.mokoji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests()
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/login")
                .successHandler((request, response, authentication) -> response.sendRedirect("/"));

        return http.build();
    }
}
