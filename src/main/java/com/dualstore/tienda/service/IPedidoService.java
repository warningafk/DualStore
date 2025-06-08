package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Pedido;
import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> buscarTodos();
    void guardar(Pedido pedido);
    void modificar(Pedido pedido);
    Optional<Pedido> buscarId(Integer id);
    void eliminar(Integer id);
}
