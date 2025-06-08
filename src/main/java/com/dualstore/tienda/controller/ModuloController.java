package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Modulo;
import com.dualstore.tienda.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class ModuloController {

    @Autowired
    private IModuloService moduloService;

    @GetMapping("/modulos")
    public List<Modulo> buscarTodos() {
        return moduloService.buscarTodos();
    }

    @PostMapping("/modulos")
    public Modulo guardar(@RequestBody Modulo modulo) {
        moduloService.guardar(modulo);
        return modulo;
    }

    @PutMapping("/modulos")
    public Modulo modificar(@RequestBody Modulo modulo) {
        moduloService.modificar(modulo);
        return modulo;
    }

    @GetMapping("/modulos/{id}")
    public Optional<Modulo> buscarId(@PathVariable("id") Integer id) {
        return moduloService.buscarId(id);
    }

    @DeleteMapping("/modulos/{id}")
    public String eliminar(@PathVariable Integer id) {
        moduloService.eliminar(id);
        return "Módulo eliminado";
    }
}
