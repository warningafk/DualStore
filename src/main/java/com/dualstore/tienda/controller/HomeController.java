package com.dualstore.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.repository.UsuarioRepository;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping({"/", "/principal"})
    public String mostrarPrincipal(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String correo = authentication.getName();
            Usuario usuario = usuarioRepository.findAll().stream()
                    .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                    .findFirst().orElse(null);
            if (usuario != null) {
                model.addAttribute("nombreUsuario", usuario.getNombreCompleto());
            }
        }
        return "front-principal/principal"; // Busca front-principal/principal.html en templates
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Busca login.html en templates
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard"; // Busca dashboard.html en templates
    }

    @GetMapping("/admin/login")
    public String mostrarAdminLogin() {
        return "admin/login"; // Busca /admin/login.html en templates
    }
}