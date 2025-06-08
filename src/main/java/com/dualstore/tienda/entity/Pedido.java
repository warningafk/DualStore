package com.dualstore.tienda.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

/**
 * Entidad que mapea la tabla “pedidos” con borrado lógico vía el campo estado.
 */
@Entity
@Table(name = "pedidos")
@SQLDelete(sql = "UPDATE pedidos SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_pedido", length = 20, nullable = false, unique = true)
    private String codigoPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fecha", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fecha = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido", length = 20, nullable = false)
    private PedidoEstado estadoPedido = PedidoEstado.Pendiente;

    @Enumerated(EnumType.STRING)
    @Column(name = "calificacion", length = 20)
    private Calificacion calificacion;

    /** 1 = activo, 0 = eliminado lógicamente */
    @Column(name = "estado", nullable = false)
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public Pedido() {}

    /** Para relaciones donde solo se conoce el ID */
    public Pedido(Integer id) {
        this.id = id;
    }

    /* ---------- Getters & Setters ---------- */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public PedidoEstado getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(PedidoEstado estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
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
        return "Pedido [id=" + id +
               ", codigoPedido=" + codigoPedido +
               ", usuarioId=" + (usuario != null ? usuario.getId() : null) +
               ", fecha=" + fecha +
               ", estadoPedido=" + estadoPedido +
               ", calificacion=" + calificacion +
               ", estado=" + estado +
               "]";
    }

    /**
    * Enum para el estado del pedido.
    * Coincide con los valores del ENUM en la BD.
    */
    public enum PedidoEstado {
        Pendiente,
        Entregado,
        Cancelado
    }

    /**
    * Enum para la calificación del pedido.
    * Coincide con los valores del ENUM en la BD.
    */
    public enum Calificacion {
        Mala,
        Regular,
        Buena,
        Excelente
    }
}