package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Cliente;
import com.dualstore.tienda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SesionController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("clienteUserDetailsService")
    private UserDetailsService clienteUserDetailsService;

    @GetMapping("/api/usuario-actual")
    public Map<String, String> usuarioActual(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            String correo = authentication.getName();
            // Buscar el cliente específicamente en la tabla clientes
            Cliente cliente = clienteRepository.findByCorreoAndEstado(correo, 1).orElse(null);
            if (cliente != null) {
                response.put("nombreCompleto", cliente.getNombreCompleto());
            }
        }
        return response;
    }
}
