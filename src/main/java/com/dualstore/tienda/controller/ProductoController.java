package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Producto;
import com.dualstore.tienda.service.IProductoService;
import com.dualstore.tienda.service.ICategoriaService;
import com.dualstore.tienda.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private IUnidadMedidaService unidadMedidaService;

    // Mostrar formulario de registro de producto
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.buscarTodos());
        model.addAttribute("unidadesMedida", unidadMedidaService.buscarTodos());
        return "producto/form";
    }

    // Guardar producto desde el formulario
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.buscarTodos();
        model.addAttribute("productos", productos);
        return "producto/index";
    }

    @DeleteMapping("/productos/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "Producto eliminado";
    }
}
