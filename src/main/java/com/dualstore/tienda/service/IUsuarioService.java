package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> buscarTodos();
    void guardar(Usuario usuario);
    void modificar(Usuario usuario);
    Optional<Usuario> buscarId(Integer id);
    void eliminar(Integer id);
}
