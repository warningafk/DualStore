package com.dualstore.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dualstore.tienda.entity.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {

}
