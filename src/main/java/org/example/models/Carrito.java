package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private ArrayList<Producto> productos;
    private double total = 0;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void agregarProducto(Producto producto) {
        System.out.println("Agregando producto al carrito: " + producto);
        productos.add(producto);
        total += producto.getPrecio();
    }

    public void eliminarProducto(Producto producto) {
        if (productos.contains(producto)) {
            productos.remove(producto);
            System.out.println("Producto eliminado del carrito: " + producto);
        } else {
            System.out.println("El producto no está en el carrito.");
        }
        System.out.println("Productos en el carrito: " + productos);
    }

    public void mostrarCarrito() {
        System.out.println("Contenido del carrito:");
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
        System.out.println("Total a pagar:" + this.total);
    }




}
