package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.Pedido;
import com.dualstore.tienda.repository.PedidoRepository;
import com.dualstore.tienda.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public void guardar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public void modificar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarId(Integer id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
