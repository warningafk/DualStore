package com.dualstore.tienda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ADMIN_SECURITY_CONTEXT_KEY = "ADMIN_SPRING_SECURITY_CONTEXT";
    public static final String CLIENT_SECURITY_CONTEXT_KEY = "CLIENT_SPRING_SECURITY_CONTEXT";

    @Autowired
    private AdminAuthenticationSuccessHandler adminSuccessHandler;

    @Autowired
    private ClienteAuthenticationSuccessHandler clienteSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Qualifier("adminSecurityContextRepository")
    public SecurityContextRepository adminSecurityContextRepository() {
        HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
        repository.setSpringSecurityContextKey(ADMIN_SECURITY_CONTEXT_KEY);
        return repository;
    }

    @Bean
    @Qualifier("clientSecurityContextRepository")
    public SecurityContextRepository clientSecurityContextRepository() {
        HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
        repository.setSpringSecurityContextKey(CLIENT_SECURITY_CONTEXT_KEY);
        return repository;
    }

    @Bean
    public SecurityContextLogoutHandler customLogoutHandler() {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setInvalidateHttpSession(false);
        return logoutHandler;
    }

    // Configuración exclusiva para ADMINISTRADORES
    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurity(HttpSecurity http,
            @Qualifier("adminUserDetailsService") UserDetailsService adminUserDetailsService,
            @Qualifier("adminSecurityContextRepository") SecurityContextRepository adminSecurityContextRepository,
            SecurityContextLogoutHandler customLogoutHandler) // Inyectar el bean
            throws Exception {
        return http
                .securityMatcher("/admin/**") // Solo rutas que empiecen con /admin/
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login", "/admin/css/**", "/admin/js/**", "/admin/img/**")
                        .permitAll()
                        .anyRequest().hasRole("ADMIN"))
                .formLogin(form -> form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/loginProcess") // URL diferente para procesar admin
                        .successHandler(adminSuccessHandler)
                        .failureUrl("/admin/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login?logout")
                        .addLogoutHandler((request, response, authentication) -> { // Limpiar llave de sesión admin
                            HttpSession session = request.getSession(false);
                            if (session != null) {
                                session.removeAttribute(ADMIN_SECURITY_CONTEXT_KEY);
                            }
                        })
                        .addLogoutHandler(customLogoutHandler)) // Usar el bean configurado
                .userDetailsService(adminUserDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(fixation -> fixation.changeSessionId()) // Cambiado de newSession() a changeSessionId()
                        .maximumSessions(1) // Configura máximo de sesiones
                        .maxSessionsPreventsLogin(false) // Comportamiento si se excede el máximo
                )
                .securityContext(context -> context
                        .securityContextRepository(adminSecurityContextRepository) // Usar repo específico
                )
                .csrf(csrf -> csrf.disable())
                .build();
    }

    // Configuración exclusiva para CLIENTES
    @Bean
    @Order(2)
    public SecurityFilterChain clienteSecurity(HttpSecurity http,
            @Qualifier("clienteUserDetailsService") UserDetailsService clienteUserDetailsService,
            @Qualifier("clientSecurityContextRepository") SecurityContextRepository clientSecurityContextRepository,
            SecurityContextLogoutHandler customLogoutHandler) // Inyectar el bean
            throws Exception {
        return http
                // No securityMatcher, se aplica al resto
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/principal", "/login", "/registro",
                                "/css/**", "/js/**", "/img/**", "/api/usuario-actual",
                                "/logout", "/front-principal/**", "/fragments/**", "/debug/**", "/contactanos")
                        .permitAll()
                        .requestMatchers("/dashboard/**").hasRole("CLIENTE") // Ejemplo de ruta protegida para cliente
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/clienteLoginProcess") // URL diferente para procesar cliente
                        .successHandler(clienteSuccessHandler)
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/principal") // <--- CAMBIO AQUÍ: Redirigir a /principal
                        .addLogoutHandler((request, response, authentication) -> { // Limpiar llave de sesión cliente
                            HttpSession session = request.getSession(false);
                            if (session != null) {
                                session.removeAttribute(CLIENT_SECURITY_CONTEXT_KEY);
                            }
                        })
                        .addLogoutHandler(customLogoutHandler)) // Usar el bean configurado
                .userDetailsService(clienteUserDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(fixation -> fixation.changeSessionId()) // Cambiado de newSession() a changeSessionId()
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false))
                .securityContext(context -> context
                        .securityContextRepository(clientSecurityContextRepository) // Usar repo específico
                )
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
