package com.dualstore.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dualstore.tienda.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
