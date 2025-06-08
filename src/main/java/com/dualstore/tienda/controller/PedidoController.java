package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.Pedido;
import com.dualstore.tienda.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping("/pedidos")
    public List<Pedido> buscarTodos() {
        return pedidoService.buscarTodos();
    }

    @PostMapping("/pedidos")
    public Pedido guardar(@RequestBody Pedido pedido) {
        pedidoService.guardar(pedido);
        return pedido;
    }

    @PutMapping("/pedidos")
    public Pedido modificar(@RequestBody Pedido pedido) {
        pedidoService.modificar(pedido);
        return pedido;
    }

    @GetMapping("/pedidos/{id}")
    public Optional<Pedido> buscarId(@PathVariable("id") Integer id) {
        return pedidoService.buscarId(id);
    }

    @DeleteMapping("/pedidos/{id}")
    public String eliminar(@PathVariable Integer id) {
        pedidoService.eliminar(id);
        return "Pedido eliminado";
    }
}
