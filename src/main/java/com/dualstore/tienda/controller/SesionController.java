package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SesionController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/api/usuario-actual")
    public Map<String, String> usuarioActual(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            String correo = authentication.getName(); // ahora siempre es el correo
            Usuario usuario = usuarioRepository.findAll().stream()
                    .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                    .findFirst().orElse(null);
            if (usuario != null) {
                response.put("nombreCompleto", usuario.getNombreCompleto());
            }
        }
        return response;
    }
}
