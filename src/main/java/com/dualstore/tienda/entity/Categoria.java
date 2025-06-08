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
 * Entidad que representa la tabla “categorias”.
 * Implementa borrado lógico mediante el campo {@code estado}.
 */
@Entity
@Table(name = "categorias")
@SQLDelete(sql = "UPDATE categorias SET estado = 0 WHERE idcategoria = ?")
@Where(clause = "estado = 1")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcategoria;

    @Column(length = 20, nullable = false, unique = true)
    private String codigo;

    @Column(length = 100, nullable = false)
    private String nombre;

    private String descripcion;

    /** 1 = activo, 0 = eliminado lógicamente */
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public Categoria() {}

    /** Útil para relaciones (FK) donde solo se necesita el ID */
    public Categoria(Integer id) {
        this.idcategoria = id;
    }

    /* ---------- Getters & Setters ---------- */

    public Integer getIdcategoria() { return idcategoria; }
    public void setIdcategoria(Integer idcategoria) { this.idcategoria = idcategoria; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    /* ---------- toString ---------- */

    @Override
    public String toString() {
        return "Categoria [idcategoria=" + idcategoria +
               ", codigo=" + codigo +
               ", nombre=" + nombre +
               ", descripcion=" + descripcion +
               ", estado=" + estado + "]";
    }
}
