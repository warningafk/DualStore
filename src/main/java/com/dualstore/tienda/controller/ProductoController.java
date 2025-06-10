package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Producto;
import com.dualstore.tienda.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.buscarTodos();
        model.addAttribute("productos", productos);
        return "producto/index";
    }

    @PostMapping("/productos")
    public Producto guardar(@RequestBody Producto producto) {
        productoService.guardar(producto);
        return producto;
    }

    @PutMapping("/productos")
    public Producto modificar(@RequestBody Producto producto) {
        productoService.modificar(producto);
        return producto;
    }

    @GetMapping("/productos/{id}")
    public Optional<Producto> buscarId(@PathVariable("id") Integer id) {
        return productoService.buscarId(id);
    }

    @DeleteMapping("/productos/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "Producto eliminado";
    }
}
