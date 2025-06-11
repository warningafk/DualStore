package com.dualstore.tienda.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Manejador de éxito específico para clientes
 * Siempre redirige a la vista principal
 */
@Component
public class ClienteAuthenticationSuccessHandler implements AuthenticationSuccessHandler {    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
          // Obtener la sesión actual (no invalidar, ya que contiene la autenticación)
        HttpSession session = request.getSession(true);
        session.setAttribute("userType", "CLIENTE");
        session.setAttribute("clienteUser", authentication.getName());
        
        // Configurar cookie específica para cliente
        jakarta.servlet.http.Cookie clienteCookie = new jakarta.servlet.http.Cookie("CLIENT_JSESSIONID", session.getId());
        clienteCookie.setPath("/");
        clienteCookie.setHttpOnly(true);
        clienteCookie.setMaxAge(60 * 60); // 60 minutos
        response.addCookie(clienteCookie);
        
        // Los clientes siempre van a la vista principal
        response.sendRedirect("/principal");
    }
}
