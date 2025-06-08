package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.Rol;
import com.dualstore.tienda.repository.RolRepository;
import com.dualstore.tienda.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> buscarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public void guardar(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void modificar(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> buscarId(Integer id) {
        return rolRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        rolRepository.deleteById(id);
    }
}
