package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Categoria;
import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> buscarTodos();
    void guardar(Categoria categoria);
    void modificar(Categoria categoria);
    Optional<Categoria> buscarId(Integer id);
    void eliminar(Integer id);
}
