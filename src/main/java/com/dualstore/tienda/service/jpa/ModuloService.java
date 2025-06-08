package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.Modulo;
import com.dualstore.tienda.repository.ModuloRepository;
import com.dualstore.tienda.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloService implements IModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public List<Modulo> buscarTodos() {
        return moduloRepository.findAll();
    }

    @Override
    public void guardar(Modulo modulo) {
        moduloRepository.save(modulo);
    }

    @Override
    public void modificar(Modulo modulo) {
        moduloRepository.save(modulo);
    }

    @Override
    public Optional<Modulo> buscarId(Integer id) {
        return moduloRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        moduloRepository.deleteById(id);
    }
}
