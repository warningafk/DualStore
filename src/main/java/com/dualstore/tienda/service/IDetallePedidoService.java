package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.DetallePedido;
import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    List<DetallePedido> buscarTodos();
    void guardar(DetallePedido detallePedido);
    void modificar(DetallePedido detallePedido);
    Optional<DetallePedido> buscarId(Integer id);
    void eliminar(Integer id);
}
