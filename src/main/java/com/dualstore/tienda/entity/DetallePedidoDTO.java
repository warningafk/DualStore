package com.dualstore.tienda.entity;

import java.math.BigDecimal;

public class DetallePedidoDTO {
    private Integer id;
    private Integer pedidoId;
    private String codigoPedido;
    private Integer productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;

    public DetallePedidoDTO() {}

    public DetallePedidoDTO(Integer id, Integer pedidoId, String codigoPedido,
                            Integer productoId, String nombreProducto,
                            Integer cantidad, BigDecimal precioUnitario) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.codigoPedido = codigoPedido;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }
    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Integer getProductoId() {
        return productoId;
    }
    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
    @Override
    public String toString() {
        return "DetallePedidoDTO [id=" + id +
               ", pedidoId=" + pedidoId +
               ", codigoPedido=" + codigoPedido +
               ", productoId=" + productoId +
               ", nombreProducto=" + nombreProducto +
               ", cantidad=" + cantidad +
               ", precioUnitario=" + precioUnitario +
               "]";
    }
}
