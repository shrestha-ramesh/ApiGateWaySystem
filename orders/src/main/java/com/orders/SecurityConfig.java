package com.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new IpAddressMatcher("127.0.0.1")) // Allow only localhost access
                                 .permitAll()
                                .anyRequest()
                                .authenticated()
        ).csrf(csrf -> csrf.disable());
        return http.build();
    }
}
