package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Cliente;
import com.dualstore.tienda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        // Borrado lógico
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Cliente c = cliente.get();
            c.setEstado(0);
            clienteRepository.save(c);
        }
    }

    @Override
    public Optional<Cliente> findByCorreo(String correo) {
        return clienteRepository.findByCorreoAndEstado(correo, 1);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return clienteRepository.existsByCorreo(correo);
    }

    @Override
    public List<Cliente> findByEstado(Integer estado) {
        return clienteRepository.findByEstado(estado);
    }

    @Override
    public Long contarClientesActivos() {
        return clienteRepository.contarClientesActivos();
    }
}
