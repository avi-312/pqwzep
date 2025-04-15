package com.pqwz.pqwzep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // 🔓 Allow login access
                        .anyRequest().authenticated()                // 🔐 Protect others
                )
                .formLogin(login -> login.disable());            // ❌ Disable Spring's default login form

        return http.build();
    }
}
