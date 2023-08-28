package com.springprojects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    // Enabling ONLY HttpBasic Authentication
    // NOT RECOMMENDED FOR PRODUCTION
    // Authentication is done by sending base64 encoded in headers, which can be easily decoded
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> {
            try {
                csrf.disable().authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }
}
