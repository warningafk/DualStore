package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        usuarioService.guardar(usuario);
        return usuario;
    }

    @PutMapping("/usuarios")
    public Usuario modificar(@RequestBody Usuario usuario) {
        usuarioService.modificar(usuario);
        return usuario;
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> buscarId(@PathVariable("id") Integer id) {
        return usuarioService.buscarId(id);
    }

    @DeleteMapping("/usuarios/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "Usuario eliminado";
    }
}
