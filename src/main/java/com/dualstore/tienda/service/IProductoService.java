package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> buscarTodos();
    void guardar(Producto producto);
    void modificar(Producto producto);
    Optional<Producto> buscarId(Integer id);
    void eliminar(Integer id);
}
