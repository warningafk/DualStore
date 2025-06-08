package com.dualstore.tienda.entity;

import java.math.BigDecimal;

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
 * Entidad que mapea la tabla “productos” con borrado lógico vía estado.
 */
@Entity
@Table(name = "productos")
@SQLDelete(sql = "UPDATE productos SET estado = 0 WHERE id = ?")
@Where(clause = "estado = 1")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 50)
    private String marca;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 100)
    private String talla;

    @Column(name = "precio_compra", precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "precio_venta", precision = 10, scale = 2)
    private BigDecimal precioVenta;

    /** Stock disponible */
    private Integer stock = 0;

    @Column(length = 255)
    private String foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_medida_id")
    private UnidadMedida unidadMedida;

    /** 1 = activo, 0 = eliminado lógicamente */
    private Integer estado = 1;

    /* ---------- Constructores ---------- */

    public Producto() { }

    /** Para relaciones en que sólo se necesita el ID */
    public Producto(Integer id) {
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

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }
    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
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
        return "Producto [id=" + id + 
                ", nombre=" + nombre + 
                ", marca=" + marca + 
                ", descripcion=" + descripcion + 
                ", talla=" + talla + 
                ", precioCompra=" + precioCompra + 
                ", precioVenta=" + precioVenta + 
                ", stock="+ stock + 
                ", foto=" + foto + 
                ", categoria=" + categoria + 
                ", unidadMedida=" + unidadMedida + 
                ", estado="+ estado + "]";
    }
}
