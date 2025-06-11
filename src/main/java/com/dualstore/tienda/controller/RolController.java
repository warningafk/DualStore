package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Rol;
import com.dualstore.tienda.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/roles")
public class RolController {

    @Autowired
    private IRolService rolService;


    @GetMapping("")
    public String listarRoles(Model model) {
        List<Rol> roles = rolService.buscarTodos();
        model.addAttribute("roles", roles);
        return "rol/index";
    }

    @PostMapping("/roles")
    public Rol guardar(@RequestBody Rol rol) {
        rolService.guardar(rol);
        return rol;
    }

    @PutMapping("/roles")
    public Rol modificar(@RequestBody Rol rol) {
        rolService.modificar(rol);
        return rol;
    }

    @GetMapping("/roles/{id}")
    public Optional<Rol> buscarId(@PathVariable("id") Integer id) {
        return rolService.buscarId(id);
    }

    @DeleteMapping("/roles/{id}")
    public String eliminar(@PathVariable Integer id) {
        rolService.eliminar(id);
        return "Rol eliminado";
    }
}
