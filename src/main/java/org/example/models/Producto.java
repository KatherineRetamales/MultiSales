package org.example.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


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
    @OneToMany(mappedBy ="producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos= new ArrayList<>();




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
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


}
