package com.dualstore.tienda.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa la tabla “modulos” con borrado lógico vía estado.
 */
@Entity
@Table(name = "modulo")
@SQLDelete(sql = "UPDATE modulos SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    /** 1 = activo, 0 = eliminado lógicamente */
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public Modulo() { }

    /** Constructor útil para relaciones donde solo se conoce el ID */
    public Modulo(Integer id) {
        this.id = id;
    }

    /* ---------- Getters & Setters ---------- */

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /* ---------- toString ---------- */

    @Override
    public String toString() {
        return "Modulo [id=" + id +
               ", nombre=" + nombre +
               ", estado=" + estado + "]";
    }
}