package org.example.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    private String metodoPago;
    private Integer idEnvio;
    private int cantidadProducto;
    private Date fechaCompra;
    private double totalCompra;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido_Producto> pedido_productos = new ArrayList<>();

    public Pedido() {}
    public Pedido(String metodoPago, Integer idEnvio, int cantidadProducto, Date fechaCompra, double totalCompra) {
        this.metodoPago = metodoPago;
        this.idEnvio = idEnvio;
        this.cantidadProducto = cantidadProducto;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
    }

    public static boolean realizarPago() {
        return false;
    }

    public static void generarBoleta() {
    }


    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<Pedido_Producto> getPedido_productos() {
        return pedido_productos;
    }

    public void setPedido_productos(List<Pedido_Producto> pedido_productos) {
        this.pedido_productos = pedido_productos;
    }

    public void addProductoProducto(Pedido_Producto pedido_producto) {
        pedido_productos.add(pedido_producto);
        pedido_producto.setPedido(this);
    }
}
