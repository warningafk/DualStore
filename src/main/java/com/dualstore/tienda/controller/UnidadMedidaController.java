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

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("unidadMedida", new UnidadMedida());
        return "unidadmedida/form";
    }

    @PostMapping("/save")
    public String guardarUnidadMedida(@ModelAttribute("unidadMedida") UnidadMedida unidadMedida) {
        unidadMedidaService.guardar(unidadMedida);
        return "redirect:/admin/unidadmedida";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Optional<UnidadMedida> unidadMedida = unidadMedidaService.buscarId(id);
        if (unidadMedida.isPresent()) {
            model.addAttribute("unidadMedida", unidadMedida.get());
            return "unidadmedida/update";
        }
        return "redirect:/admin/unidadmedida";
    }

    @PostMapping("/update/{id}")
    public String actualizarUnidadMedida(@PathVariable("id") Integer id, @ModelAttribute("unidadMedida") UnidadMedida unidadMedida) {
        unidadMedida.setId(id);
        unidadMedidaService.modificar(unidadMedida);
        return "redirect:/admin/unidadmedida";
    }

    @GetMapping("/delete/{id}")
    public String eliminarUnidadMedida(@PathVariable("id") Integer id) {
        unidadMedidaService.eliminar(id);
        return "redirect:/admin/unidadmedida";
    }
}
