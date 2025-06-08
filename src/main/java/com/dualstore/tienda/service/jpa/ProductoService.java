package com.dualstore.tienda.service.jpa;

import com.dualstore.tienda.entity.Producto;
import com.dualstore.tienda.repository.ProductoRepository;
import com.dualstore.tienda.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> buscarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void modificar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> buscarId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
}
