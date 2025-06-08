package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.Permiso;
import com.dualstore.tienda.repository.PermisoRepository;
import com.dualstore.tienda.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoService implements IPermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    @Override
    public List<Permiso> buscarTodos() {
        return permisoRepository.findAll();
    }

    @Override
    public void guardar(Permiso permiso) {
        permisoRepository.save(permiso);
    }

    @Override
    public void modificar(Permiso permiso) {
        permisoRepository.save(permiso);
    }

    @Override
    public Optional<Permiso> buscarId(Integer id) {
        return permisoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        permisoRepository.deleteById(id);
    }
}
