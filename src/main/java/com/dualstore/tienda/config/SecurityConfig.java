package com.dualstore.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Configuración exclusiva para el ADMINISTRABLE
    @Bean
    @org.springframework.core.annotation.Order(1)
    public SecurityFilterChain adminSecurity(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/admin/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login", "/admin/css/**", "/admin/js/**", "/admin/img/**").permitAll()
                .anyRequest().hasRole("ADMIN")
            )
            .formLogin(form -> form
                .loginPage("/admin/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout")
            )
            .csrf(csrf -> csrf.disable())
            .build();
    }

    // Configuración exclusiva para el PRINCIPAL (clientes)
    @Bean
    @org.springframework.core.annotation.Order(2)
    public SecurityFilterChain principalSecurity(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/login", "/principal/**", "/front-principal/**", "/", "/css/**", "/img/**", "/js/**", "/registro", "/api/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/img/**", "/js/**", "/registro", "/", "/principal", "/front-principal/**", "/api/usuario-actual").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/principal", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/principal")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .build();
    }
}
