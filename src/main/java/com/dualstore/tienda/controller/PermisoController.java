package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Permiso;
import com.dualstore.tienda.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping("/permisos")
    public List<Permiso> buscarTodos() {
        return permisoService.buscarTodos();
    }

    @PostMapping("/permisos")
    public Permiso guardar(@RequestBody Permiso permiso) {
        permisoService.guardar(permiso);
        return permiso;
    }

    @PutMapping("/permisos")
    public Permiso modificar(@RequestBody Permiso permiso) {
        permisoService.modificar(permiso);
        return permiso;
    }

    @GetMapping("/permisos/{id}")
    public Optional<Permiso> buscarId(@PathVariable("id") Integer id) {
        return permisoService.buscarId(id);
    }

    @DeleteMapping("/permisos/{id}")
    public String eliminar(@PathVariable Integer id) {
        permisoService.eliminar(id);
        return "Permiso eliminado";
    }
}
