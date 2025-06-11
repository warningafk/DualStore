package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Producto;
import com.dualstore.tienda.service.IProductoService;
import com.dualstore.tienda.service.ICategoriaService;
import com.dualstore.tienda.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin/productos")
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
    }    // Guardar producto desde el formulario
    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("producto") Producto producto,
                                @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {
        // Manejar subida de imagen si se proporciona
        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                // Crear directorio si no existe
                String uploadDir = "src/main/resources/static/img/productos/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                // Generar nombre único para el archivo
                String fileName = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                
                // Guardar archivo
                Files.copy(imagenFile.getInputStream(), filePath);
                
                // Establecer la ruta de la imagen en el producto
                producto.setFoto("/img/productos/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // En caso de error, mantener la URL si existe
            }
        }
        
        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.buscarTodos();
        model.addAttribute("productos", productos);
        return "producto/index";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarId(id).orElse(null);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.buscarTodos());
            model.addAttribute("unidadesMedida", unidadMedidaService.buscarTodos());
            return "producto/update";
        }
        return "redirect:/admin/productos";
    }    @PostMapping("/update/{id}")
    public String actualizarProducto(@PathVariable("id") Integer id, 
                                   @ModelAttribute("producto") Producto producto,
                                   @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {
        // Manejar subida de imagen si se proporciona
        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                // Crear directorio si no existe
                String uploadDir = "src/main/resources/static/img/productos/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                // Generar nombre único para el archivo
                String fileName = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                
                // Guardar archivo
                Files.copy(imagenFile.getInputStream(), filePath);
                
                // Establecer la ruta de la imagen en el producto
                producto.setFoto("/img/productos/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // En caso de error, mantener la imagen existente
            }
        }
        
        producto.setId(id);
        productoService.modificar(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/view/{id}")
    public String verProducto(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarId(id).orElse(null);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "producto/view";
        }
        return "redirect:/admin/productos";
    }

    @GetMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos";
    }
}
