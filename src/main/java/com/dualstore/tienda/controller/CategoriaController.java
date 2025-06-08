package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Categoria;
import com.dualstore.tienda.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> buscarTodos() {
        return categoriaService.buscarTodos();
    }

    @PostMapping("/categorias")
    public Categoria guardar(@RequestBody Categoria categoria) {
        categoriaService.guardar(categoria);
        return categoria;
    }

    @PutMapping("/categorias")
    public Categoria modificar(@RequestBody Categoria categoria) {
        categoriaService.modificar(categoria);
        return categoria;
    }

    @GetMapping("/categorias/{id}")
    public Optional<Categoria> buscarId(@PathVariable("id") Integer id) {
        return categoriaService.buscarId(id);
    }

    @DeleteMapping("/categorias/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return "Categoría eliminada";
    }
}