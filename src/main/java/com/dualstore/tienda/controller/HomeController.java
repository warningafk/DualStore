package com.dualstore.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/principal"})
    public String mostrarPrincipal() {
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