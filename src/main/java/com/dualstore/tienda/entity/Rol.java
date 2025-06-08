package com.dualstore.tienda.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa la tabla “roles” con borrado lógico vía estado.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "rol")
@SQLDelete(sql = "UPDATE rol SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;

    /** 1 = activo, 0 = eliminado lógicamente */
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public Rol() {}

    /* Útil para relaciones donde solo se conoce el ID */
    public Rol(Integer id) {
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
        return "Rol [id=" + id +
               ", nombre=" + nombre +
               ", estado=" + estado + "]";
    }
}