package com.dualstore.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String handleLogout(@RequestParam(value = "adminAccess", required = false) String adminAccess,
                              HttpServletRequest request, HttpServletResponse response,
                              RedirectAttributes redirectAttributes) {
        
        // Si es un admin intentando acceder a rutas de cliente, forzar logout
        if ("denied".equals(adminAccess)) {
            // Invalidar la sesión admin
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            
            // Limpiar cookies
            jakarta.servlet.http.Cookie adminCookie = new jakarta.servlet.http.Cookie("ADMIN_JSESSIONID", null);
            adminCookie.setMaxAge(0);
            adminCookie.setPath("/");
            response.addCookie(adminCookie);
            
            redirectAttributes.addFlashAttribute("message", "Para acceder como cliente, debe cerrar sesión de administrador primero.");
            return "redirect:/login";
        }
        
        // Logout normal
        return "redirect:/login";
    }
    
    @PostMapping("/logout")
    public String postLogout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidar sesión
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Limpiar cookies
        jakarta.servlet.http.Cookie clientCookie = new jakarta.servlet.http.Cookie("CLIENT_JSESSIONID", null);
        clientCookie.setMaxAge(0);
        clientCookie.setPath("/");
        response.addCookie(clientCookie);
        
        return "redirect:/login";
    }
    
    @PostMapping("/admin/logout")
    public String adminLogout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidar sesión admin
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Limpiar cookies admin
        jakarta.servlet.http.Cookie adminCookie = new jakarta.servlet.http.Cookie("ADMIN_JSESSIONID", null);
        adminCookie.setMaxAge(0);
        adminCookie.setPath("/");
        response.addCookie(adminCookie);
        
        return "redirect:/admin/login?logout";
    }
}
