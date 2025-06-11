package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Categoria;
import com.dualstore.tienda.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("")
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.buscarTodos();
        model.addAttribute("categorias", categorias);
        return "categoria/index";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }    @PostMapping("/save")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Optional<Categoria> categoria = categoriaService.buscarId(id);
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            return "categoria/update";
        }
        return "redirect:/admin/categoria";
    }

    @PostMapping("/update/{id}")
    public String actualizarCategoria(@PathVariable("id") Integer id, @ModelAttribute("categoria") Categoria categoria) {
        categoria.setIdcategoria(id);
        categoriaService.modificar(categoria);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/view/{id}")
    public String verCategoria(@PathVariable("id") Integer id, Model model) {
        Optional<Categoria> categoria = categoriaService.buscarId(id);
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            return "categoria/view";
        }
        return "redirect:/admin/categoria";
    }    @GetMapping("/delete/{id}")
    public String eliminarCategoria(@PathVariable("id") Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/admin/categoria";
    }
}