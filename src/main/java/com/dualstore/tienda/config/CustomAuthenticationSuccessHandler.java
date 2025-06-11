package com.dualstore.tienda.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manejador personalizado de éxito de autenticación para redirigir 
 * a diferentes páginas según el rol del usuario
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        // Verificar si es un administrador
        boolean isAdmin = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        // Verificar si es un cliente
        boolean isCliente = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CLIENTE"));
        
        String redirectURL = "";
        
        if (isAdmin) {
            redirectURL = "/admin/dashboard";
        } else if (isCliente) {
            redirectURL = "/principal";
        } else {
            // En caso de que no tenga ningún rol conocido, redirigir al login
            redirectURL = "/login?error";
        }
        
        response.sendRedirect(redirectURL);
    }
}
