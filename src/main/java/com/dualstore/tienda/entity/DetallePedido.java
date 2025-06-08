package com.dualstore.tienda.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

/**
 * Entidad que mapea la tabla “detalle_pedidos” con borrado lógico vía el campo estado.
 */
@Entity
@Table(name = "detalle_pedido")
@SQLDelete(sql = "UPDATE detalle_pedidos SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "estado", nullable = false)
    private Integer estado = 1;


    public DetallePedido() { }

    /** Constructor útil para relaciones donde solo se conoce el ID */
    public DetallePedido(Integer id) {
        this.id = id;
    }

    // --- Getters & Setters ---

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
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
        return "DetallePedido [id=" + id +
               ", pedidoId=" + (pedido != null ? pedido.getId() : null) +
               ", productoId=" + (producto != null ? producto.getId() : null) +
               ", cantidad=" + cantidad +
               ", precioUnitario=" + precioUnitario +
               ", estado=" + estado +
               "]";
    }
}