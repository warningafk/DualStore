package com.dualstore.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.entity.Cliente;
import com.dualstore.tienda.repository.UsuarioRepository;
import com.dualstore.tienda.repository.ClienteRepository;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;    // RUTAS PARA CLIENTES
    @GetMapping({"/", "/principal"})
    public String mostrarPrincipal(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Verificar si es admin - Los admins NO pueden acceder a la vista principal
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            
            // Si es admin, forzar logout y redirigir al login de cliente
            if (isAdmin) {
                return "redirect:/logout?adminAccess=denied";
            }
            
            // Solo permitir acceso a clientes
            boolean isCliente = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CLIENTE"));
            
            if (isCliente) {
                String correo = authentication.getName();
                Cliente cliente = clienteRepository.findByCorreoAndEstado(correo, 1).orElse(null);
                if (cliente != null) {
                    model.addAttribute("nombreUsuario", cliente.getNombreCompleto());
                }
            }
        }
        return "front-principal/principal";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }    // RUTAS PARA ADMINISTRADORES - TODO bajo /admin/
    @GetMapping("/admin/dashboard")
    public String mostrarAdminDashboard(Authentication authentication, Model model) {
        System.out.println("===== DASHBOARD CONTROLLER =====");
        System.out.println("Authentication: " + authentication);
        
        if (authentication == null) {
            System.out.println("Authentication is null - redirecting to admin login");
            return "redirect:/admin/login";
        }
        
        if (!authentication.isAuthenticated()) {
            System.out.println("Authentication is not authenticated - redirecting to admin login");
            return "redirect:/admin/login";
        }
        
        System.out.println("User: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        
        // Verificar que sea específicamente un admin
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        System.out.println("Is admin: " + isAdmin);
        
        // Si no es admin, redirigir al login de admin
        if (!isAdmin) {
            System.out.println("User is not admin - redirecting to admin login");
            return "redirect:/admin/login?error";
        }
        
        String correo = authentication.getName();
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                .filter(u -> u.getEstado() == 1)
                .findFirst().orElse(null);
        if (usuario != null) {
            model.addAttribute("nombreAdmin", usuario.getNombreCompleto());
            System.out.println("Admin user found: " + usuario.getNombreCompleto());
        } else {
            System.out.println("Admin user not found in database for email: " + correo);
        }
        
        System.out.println("Returning dashboard template");
        System.out.println("===== END DASHBOARD CONTROLLER =====");
        return "dashboard";
    }

    @GetMapping("/admin/login")
    public String mostrarAdminLogin() {
        return "admin/login";
    }
}