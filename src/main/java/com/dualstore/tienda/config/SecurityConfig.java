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
        http
            .securityMatcher("/admin/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login", "/admin/css/**", "/admin/js/**", "/admin/img/**").permitAll()
                .anyRequest().hasRole("ADMIN")
            )
            .formLogin(form -> form
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout")
            );
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    // Configuración exclusiva para el PRINCIPAL (clientes)
    @Bean
    @org.springframework.core.annotation.Order(2)
    public SecurityFilterChain principalSecurity(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/principal/**", "/front-principal/**", "/", "/css/**", "/img/**", "/js/**", "/registro")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/img/**", "/js/**", "/registro", "/", "/principal", "/front-principal/**").permitAll()
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
            );
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
