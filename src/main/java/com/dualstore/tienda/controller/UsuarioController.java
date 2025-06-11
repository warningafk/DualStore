package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.entity.Rol;
import com.dualstore.tienda.service.IUsuarioService;
import com.dualstore.tienda.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuario/index";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Rol> roles = rolService.buscarTodos();
        model.addAttribute("roles", roles);
        return "usuario/form";
    }

    @PostMapping("/save")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        // Encriptar la contraseña antes de guardar
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);
        usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            List<Rol> roles = rolService.buscarTodos();
            model.addAttribute("roles", roles);
            return "usuario/update";
        }
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/update/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute("usuario") Usuario usuario) {
        // Buscar el usuario existente para mantener la contraseña si no se cambia
        Optional<Usuario> usuarioExistente = usuarioService.buscarId(id);
        if (usuarioExistente.isPresent()) {
            Usuario existing = usuarioExistente.get();
            usuario.setId(id);
            
            // Si la contraseña está vacía, mantener la existente
            if (usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()) {
                usuario.setContrasena(existing.getContrasena());
            } else {
                // Si se proporciona nueva contraseña, encriptarla
                String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
                usuario.setContrasena(contrasenaEncriptada);
            }
            
            usuarioService.modificar(usuario);
        }
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }
}
