package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.DetallePedido;
import com.dualstore.tienda.repository.DetallePedidoRepository;
import com.dualstore.tienda.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService implements IDetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<DetallePedido> buscarTodos() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public void guardar(DetallePedido detallePedido) {
        detallePedidoRepository.save(detallePedido);
    }

    @Override
    public void modificar(DetallePedido detallePedido) {
        detallePedidoRepository.save(detallePedido);
    }

    @Override
    public Optional<DetallePedido> buscarId(Integer id) {
        return detallePedidoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}
