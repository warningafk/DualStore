package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Modulo;
import java.util.List;
import java.util.Optional;

public interface IModuloService {
    List<Modulo> buscarTodos();
    void guardar(Modulo modulo);
    void modificar(Modulo modulo);
    Optional<Modulo> buscarId(Integer id);
    void eliminar(Integer id);
}
