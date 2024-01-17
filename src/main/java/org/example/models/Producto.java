package org.example.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long idProducto;
    private String nombreProducto;

    private int stock;
    private String descripcionProducto;
    private double precio;


    //Relaciones
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido_Producto> pedido_productos = new ArrayList<>();


    //Constructores
    public Producto() {
    }

    public Producto(String nombreProducto, int stock, String descripcionProducto, double precio) {
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
    }

    //Getters and setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    //getters and setters de las relaciones
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Pedido_Producto> getPedido_productos() {
        return pedido_productos;
    }

    public void setPedido_productos(List<Pedido_Producto> pedido_productos) {
        this.pedido_productos = pedido_productos;
    }

    @Override
    public String toString() {
        return "ID: " + idProducto + " Producto: " + nombreProducto + ", Precio: " + precio; // ajusta según las propiedades de tu Producto
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto producto = (Producto) obj;
        return Objects.equals(idProducto, producto.getIdProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

    public void addProductoProducto(Pedido_Producto pedido_producto) {
        pedido_productos.add(pedido_producto);
        pedido_producto.setProducto(this);
    }
}
