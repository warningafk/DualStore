package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.UnidadMedida;
import com.dualstore.tienda.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class UnidadMedidaController {

    @Autowired
    private IUnidadMedidaService unidadMedidaService;

    @GetMapping("/unidadesmedida")
    public List<UnidadMedida> buscarTodos() {
        return unidadMedidaService.buscarTodos();
    }

    @PostMapping("/unidadesmedida")
    public UnidadMedida guardar(@RequestBody UnidadMedida unidadMedida) {
        unidadMedidaService.guardar(unidadMedida);
        return unidadMedida;
    }

    @PutMapping("/unidadesmedida")
    public UnidadMedida modificar(@RequestBody UnidadMedida unidadMedida) {
        unidadMedidaService.modificar(unidadMedida);
        return unidadMedida;
    }

    @GetMapping("/unidadesmedida/{id}")
    public Optional<UnidadMedida> buscarId(@PathVariable("id") Integer id) {
        return unidadMedidaService.buscarId(id);
    }

    @DeleteMapping("/unidadesmedida/{id}")
    public String eliminar(@PathVariable Integer id) {
        unidadMedidaService.eliminar(id);
        return "Unidad de medida eliminada";
    }
}
