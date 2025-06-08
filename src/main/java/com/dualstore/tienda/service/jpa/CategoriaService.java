package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.Categoria;
import com.dualstore.tienda.repository.CategoriaRepository;
import com.dualstore.tienda.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void modificar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscarId(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
