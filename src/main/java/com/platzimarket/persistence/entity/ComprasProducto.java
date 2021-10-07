package com.platzimarket.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {
    //LLAVE PRIMARIA COMPUESTA
    @EmbeddedId
    private ComprasProductoPK id;
    //Datos de la tabla
    private Integer cantidad;
    private Double total;
    private Boolean estado;


    //Relaciones a llaves Foraneas Este es un detalle por eso tiene dos relaciones
    //Relacion de Compra a ComprasProducto
    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn (name = "id_compra", insertable = false, updatable = false)
    private Compra compra;
    //Relacion de Producto a ComprasPoroducto
    @ManyToOne
    @JoinColumn (name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    //Generacion de Getters and Setters
    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
