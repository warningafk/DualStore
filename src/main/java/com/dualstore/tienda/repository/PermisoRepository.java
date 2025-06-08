package com.dualstore.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dualstore.tienda.entity.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

}
