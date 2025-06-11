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
    }    // Configuración exclusiva para el ADMINISTRABLE
    @Bean
    @org.springframework.core.annotation.Order(1)
    public SecurityFilterChain adminSecurity(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/admin/**", "/dashboard", "/productos/**", "/categorias/**", "/unidadmedida/**", "/usuarios/**", "/roles/**", "/css/**", "/js/**", "/img/**", "/fragments/**")            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login", "/admin/css/**", "/admin/js/**", "/admin/img/**", "/css/**", "/js/**", "/img/**", "/fragments/**").permitAll()
                // Se admite tanto ADMIN como ADMINISTRADOR para evitar errores
                // cuando el nombre del rol en la base de datos difiere
                .anyRequest().hasAnyRole("ADMIN", "ADMINISTRADOR")
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
    }    // Configuración exclusiva para el PRINCIPAL (clientes)
    @Bean
    @org.springframework.core.annotation.Order(2)
    public SecurityFilterChain principalSecurity(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/login", "/logout", "/principal/**", "/front-principal/**", "/", "/registro", "/api/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/registro", "/", "/principal", "/front-principal/**", "/api/usuario-actual", "/logout").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/principal", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/principal")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .build();
    }
}
