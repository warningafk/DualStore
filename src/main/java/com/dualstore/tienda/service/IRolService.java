package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Rol;
import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<Rol> buscarTodos();
    void guardar(Rol rol);
    void modificar(Rol rol);
    Optional<Rol> buscarId(Integer id);
    void eliminar(Integer id);
}
