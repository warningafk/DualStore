package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
    Optional<Cliente> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    List<Cliente> findByEstado(Integer estado);
    Long contarClientesActivos();
}
