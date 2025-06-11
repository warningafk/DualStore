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
 * Manejador de éxito específico para administradores
 * Siempre redirige al dashboard de admin
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        System.out.println("===== ADMIN SUCCESS HANDLER =====");
        System.out.println("Admin authenticated: " + authentication.getName());
        System.out.println("Admin authorities: " + authentication.getAuthorities());
          // Obtener la sesión actual (no invalidar, ya que contiene la autenticación)
        HttpSession session = request.getSession(true);
        session.setAttribute("userType", "ADMIN");
        session.setAttribute("adminUser", authentication.getName());
        
        // Configurar cookie específica para admin
        jakarta.servlet.http.Cookie adminCookie = new jakarta.servlet.http.Cookie("ADMIN_JSESSIONID", session.getId());
        adminCookie.setPath("/admin");
        adminCookie.setHttpOnly(true);
        adminCookie.setMaxAge(30 * 60); // 30 minutos
        response.addCookie(adminCookie);
        
        System.out.println("Redirecting admin to: /admin/dashboard");
        System.out.println("===== END ADMIN SUCCESS HANDLER =====");
        
        // Los admins siempre van al dashboard
        response.sendRedirect("/admin/dashboard");
    }
}
