package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.UnidadMedida;
import java.util.List;
import java.util.Optional;

public interface IUnidadMedidaService {
    List<UnidadMedida> buscarTodos();
    void guardar(UnidadMedida unidadMedida);
    void modificar(UnidadMedida unidadMedida);
    Optional<UnidadMedida> buscarId(Integer id);
    void eliminar(Integer id);
}
