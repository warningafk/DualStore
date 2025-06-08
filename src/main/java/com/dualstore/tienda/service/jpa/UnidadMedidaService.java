package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.UnidadMedida;
import com.dualstore.tienda.repository.UnidadMedidaRepository;
import com.dualstore.tienda.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaService implements IUnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<UnidadMedida> buscarTodos() {
        return unidadMedidaRepository.findAll();
    }

    @Override
    public void guardar(UnidadMedida unidadMedida) {
        unidadMedidaRepository.save(unidadMedida);
    }

    @Override
    public void modificar(UnidadMedida unidadMedida) {
        unidadMedidaRepository.save(unidadMedida);
    }

    @Override
    public Optional<UnidadMedida> buscarId(Integer id) {
        return unidadMedidaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        unidadMedidaRepository.deleteById(id);
    }
}
