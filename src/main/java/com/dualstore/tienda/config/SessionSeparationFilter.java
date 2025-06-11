package com.dualstore.tienda.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Filtro para validar que las sesiones de admin y cliente estén separadas
 */
@Component
public class SessionSeparationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession(false);
          // Si hay autenticación activa
        if (auth != null && auth.isAuthenticated() && session != null) {
            
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            boolean isCliente = auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CLIENTE"));
            
            // Validaciones de separación
            if (isAdmin && requestURI.startsWith("/") && !requestURI.startsWith("/admin") 
                && !requestURI.startsWith("/logout") && !requestURI.contains("css") 
                && !requestURI.contains("js") && !requestURI.contains("img")) {
                
                // Admin intentando acceder a rutas de cliente - forzar logout
                System.out.println("ADMIN SESSION SEPARATION: Admin trying to access client route: " + requestURI);
                session.invalidate();
                SecurityContextHolder.clearContext();
                response.sendRedirect("/logout?adminAccess=denied");
                return;
            }
            
            if (isCliente && requestURI.startsWith("/admin")) {
                // Cliente intentando acceder a rutas de admin - denegar
                System.out.println("CLIENT SESSION SEPARATION: Client trying to access admin route: " + requestURI);
                response.sendRedirect("/login?error=unauthorized");
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
