package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Permiso;
import java.util.List;
import java.util.Optional;

public interface IPermisoService {
    List<Permiso> buscarTodos();
    void guardar(Permiso permiso);
    void modificar(Permiso permiso);
    Optional<Permiso> buscarId(Integer id);
    void eliminar(Integer id);
}
