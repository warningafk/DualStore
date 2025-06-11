package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.UnidadMedida;
import com.dualstore.tienda.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/unidadmedida")
public class UnidadMedidaController {

    @Autowired
    private IUnidadMedidaService unidadMedidaService;


    @GetMapping("")
    public String listarUnidades(Model model) {
        List<UnidadMedida> unidades = unidadMedidaService.buscarTodos();
        model.addAttribute("unidades", unidades);
        return "unidadmedida/index";
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
