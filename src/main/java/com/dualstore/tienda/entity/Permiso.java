package com.dualstore.tienda.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa la tabla “permisos” con borrado lógico vía estado.
 */
@Entity
@Table(name = "permiso")
@SQLDelete(sql = "UPDATE permisos SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Relación con Rol */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;

    /** Relación con Módulo */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    /** Permisos específicos */
    @Column(name = "puede_ver")
    private Boolean puedeVer = false;

    @Column(name = "puede_editar")
    private Boolean puedeEditar = false;

    @Column(name = "puede_eliminar")
    private Boolean puedeEliminar = false;

    @Column(name = "puede_crear")
    private Boolean puedeCrear = false;

    /** 1 = activo, 0 = eliminado lógicamente */
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public Permiso() {}

    /** Para crear instancias con sólo el ID (p.ej. en relaciones) */
    public Permiso(Integer id) {
        this.id = id;
    }

    /* ---------- Getters & Setters ---------- */

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Modulo getModulo() {
        return modulo;
    }
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Boolean getPuedeVer() {
        return puedeVer;
    }
    public void setPuedeVer(Boolean puedeVer) {
        this.puedeVer = puedeVer;
    }

    public Boolean getPuedeEditar() {
        return puedeEditar;
    }
    public void setPuedeEditar(Boolean puedeEditar) {
        this.puedeEditar = puedeEditar;
    }

    public Boolean getPuedeEliminar() {
        return puedeEliminar;
    }
    public void setPuedeEliminar(Boolean puedeEliminar) {
        this.puedeEliminar = puedeEliminar;
    }

    public Boolean getPuedeCrear() {
        return puedeCrear;
    }
    public void setPuedeCrear(Boolean puedeCrear) {
        this.puedeCrear = puedeCrear;
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
        return "Permiso [id=" + id +
               ", rol=" + (rol != null ? rol.getId() : null) +
               ", modulo=" + (modulo != null ? modulo.getId() : null) +
               ", puedeVer=" + puedeVer +
               ", puedeEditar=" + puedeEditar +
               ", puedeEliminar=" + puedeEliminar +
               ", puedeCrear=" + puedeCrear +
               ", estado=" + estado +
               "]";
    }
}
