package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DebugController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/debug/usuarios")
    public List<Usuario> listarUsuarios() {
        System.out.println("===== DEBUG USUARIOS =====");
        List<Usuario> usuarios = usuarioRepository.findAll();
        System.out.println("Total usuarios encontrados: " + usuarios.size());
        for (Usuario u : usuarios) {
            System.out.println("Usuario: " + u.getCorreo() + " - Estado: " + u.getEstado() + " - Rol: " + (u.getRol() != null ? u.getRol().getNombre() : "null"));
        }
        System.out.println("===== END DEBUG USUARIOS =====");
        return usuarios;
    }
}
