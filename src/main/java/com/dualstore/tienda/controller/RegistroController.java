package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.entity.Rol;
import com.dualstore.tienda.service.IUsuarioService;
import com.dualstore.tienda.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class RegistroController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombreCompleto,
                                   @RequestParam String correo,
                                   @RequestParam String direccion,
                                   @RequestParam String password,
                                   Model model) {
        // Verifica si el correo ya existe
        List<Usuario> existentes = usuarioService.buscarTodos();
        boolean existe = existentes.stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo));
        if (existe) {
            model.addAttribute("registroError", "El correo ya está registrado");
            return "registro";
        }
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setCorreo(correo);
        usuario.setDireccion(direccion);
        usuario.setContrasena(passwordEncoder.encode(password));
        usuario.setEstado(1);
        // Busca el rol cliente (debe existir en la BD)
        Rol rolCliente = rolService.buscarTodos().stream()
                .filter(r -> r.getNombre().equalsIgnoreCase("cliente"))
                .findFirst().orElse(null);
        usuario.setRol(rolCliente);
        usuarioService.guardar(usuario);
        return "redirect:/login";
    }
}
