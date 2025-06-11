package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Cliente;
import com.dualstore.tienda.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam("nombreCompleto") String nombreCompleto,
                                 @RequestParam("correo") String correo,
                                 @RequestParam("contrasena") String contrasena,
                                 @RequestParam("direccion") String direccion,
                                 @RequestParam("telefono") String telefono,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Verificar si el correo ya existe
            if (clienteService.existsByCorreo(correo)) {
                redirectAttributes.addFlashAttribute("error", "El correo electrónico ya está registrado.");
                return "redirect:/registro";
            }

            // Crear nuevo cliente
            Cliente cliente = new Cliente();
            cliente.setNombreCompleto(nombreCompleto);
            cliente.setCorreo(correo);
            cliente.setContrasena(passwordEncoder.encode(contrasena)); // Encriptar contraseña
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);

            // Guardar cliente
            clienteService.save(cliente);

            redirectAttributes.addFlashAttribute("success", "Registro exitoso. Ahora puedes iniciar sesión.");
            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar el registro. Inténtalo de nuevo.");            return "redirect:/registro";
        }
    }
}
