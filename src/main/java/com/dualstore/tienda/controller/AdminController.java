package com.dualstore.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador específico para manejar recursos estáticos del área de administración
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/css/**")
    public String adminCss() {
        return "forward:/css/";
    }

    @GetMapping("/js/**")
    public String adminJs() {
        return "forward:/js/";
    }

    @GetMapping("/img/**")
    public String adminImg() {
        return "forward:/img/";
    }
}
