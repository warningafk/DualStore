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
 * Entidad que representa la tabla “unidades_medida” con borrado lógico vía estado.
 */
@Entity
@Table(name = "unidad_medida")
@SQLDelete(sql = "UPDATE unidades_medida SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(length = 50, nullable = false)
    private String descripcion;

    /** 1 = activo, 0 = eliminado lógicamente */
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public UnidadMedida() {}

    /** Útil para relaciones donde solo se conoce el ID */
    public UnidadMedida(Integer id) {
        this.id = id;
    }

    /* ---------- Getters & Setters ---------- */

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        return "UnidadMedida [id=" + id +
               ", codigo=" + codigo +
               ", descripcion=" + descripcion +
               ", estado=" + estado + "]";
    }
}
