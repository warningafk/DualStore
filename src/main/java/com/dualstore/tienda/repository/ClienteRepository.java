package com.dualstore.tienda.repository;

import com.dualstore.tienda.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    /**
     * Buscar cliente por correo y estado activo
     */
    Optional<Cliente> findByCorreoAndEstado(String correo, Integer estado);
    
    /**
     * Buscar cliente por correo (independientemente del estado)
     */
    Optional<Cliente> findByCorreo(String correo);
    
    /**
     * Verificar si existe un cliente con ese correo
     */
    boolean existsByCorreo(String correo);
    
    /**
     * Obtener todos los clientes activos
     */
    List<Cliente> findByEstado(Integer estado);
    
    /**
     * Buscar clientes por nombre (búsqueda parcial)
     */
    @Query("SELECT c FROM Cliente c WHERE c.nombreCompleto LIKE %:nombre% AND c.estado = 1")
    List<Cliente> buscarPorNombre(@Param("nombre") String nombre);
    
    /**
     * Contar clientes activos
     */
    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 1")
    Long contarClientesActivos();
}
