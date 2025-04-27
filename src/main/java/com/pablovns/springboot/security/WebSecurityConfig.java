package com.pablovns.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/login", "/login.html").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/gerentes").hasRole("GERENTES")
                        .requestMatchers("/usuarios").hasAnyRole("USUARIOS", "GERENTES")
                        .anyRequest().authenticated()
                ).formLogin(form -> form
                        .loginPage("/login")  // Página de login personalizada (estática)
                        .permitAll()  // Permite acesso à página de login
                        .failureUrl("/login?error=true") // Redireciona de volta para /login com erro
                );
        return http.build();
    }
}
