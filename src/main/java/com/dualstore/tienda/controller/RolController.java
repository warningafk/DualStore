package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Rol;
import com.dualstore.tienda.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/roles")
public class RolController {

    @Autowired
    private IRolService rolService;


    @GetMapping("")
    public String listarRoles(Model model) {
        List<Rol> roles = rolService.buscarTodos();
        model.addAttribute("roles", roles);
        return "rol/index";
    }    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("rol", new Rol());
        return "rol/form";
    }

    @PostMapping("/save")
    public String guardar(Rol rol) {
        rolService.guardar(rol);
        return "redirect:/admin/roles";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Optional<Rol> rol = rolService.buscarId(id);
        if (rol.isPresent()) {
            model.addAttribute("rol", rol.get());
            return "rol/update";
        }
        return "redirect:/admin/roles";
    }

    @PostMapping("/update/{id}")
    public String actualizar(@PathVariable("id") Integer id, Rol rol) {
        rol.setId(id);
        rolService.modificar(rol);
        return "redirect:/admin/roles";
    }

    @GetMapping("/view/{id}")
    public String ver(@PathVariable("id") Integer id, Model model) {
        Optional<Rol> rol = rolService.buscarId(id);
        if (rol.isPresent()) {
            model.addAttribute("rol", rol.get());
            return "rol/view";
        }
        return "redirect:/admin/roles";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        rolService.eliminar(id);
        return "redirect:/admin/roles";
    }
}
