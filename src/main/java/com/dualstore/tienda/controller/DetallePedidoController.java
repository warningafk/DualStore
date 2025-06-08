package com.dualstore.tienda.controller;

import com.dualstore.tienda.entity.DetallePedido;
import com.dualstore.tienda.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dualstore")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @GetMapping("/detallepedidos")
    public List<DetallePedido> buscarTodos() {
        return detallePedidoService.buscarTodos();
    }

    @PostMapping("/detallepedidos")
    public DetallePedido guardar(@RequestBody DetallePedido detallePedido) {
        detallePedidoService.guardar(detallePedido);
        return detallePedido;
    }

    @PutMapping("/detallepedidos")
    public DetallePedido modificar(@RequestBody DetallePedido detallePedido) {
        detallePedidoService.modificar(detallePedido);
        return detallePedido;
    }

    @GetMapping("/detallepedidos/{id}")
    public Optional<DetallePedido> buscarId(@PathVariable("id") Integer id) {
        return detallePedidoService.buscarId(id);
    }

    @DeleteMapping("/detallepedidos/{id}")
    public String eliminar(@PathVariable Integer id) {
        detallePedidoService.eliminar(id);
        return "Detalle de pedido eliminado";
    }
}
