package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private int codigoProducto;
    private int cantidadProductos;
    private double subTotal;


    private ArrayList<Producto> productosCarrito = new ArrayList<Producto>();

    //Constructores

    public Carrito(){
        // productosEnCarro = new ArrayList<Carrito>();
    }

    public Carrito(int codigoProducto, int cantidadProductos, double subTotal, ArrayList<Producto> productosCarrito) {
        this.codigoProducto = codigoProducto;
        this.cantidadProductos = cantidadProductos;
        this.subTotal = subTotal;
        this.productosCarrito = productosCarrito;
    }


    //Getters and setters
    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<Producto> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductosCarrito(ArrayList<Producto> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }


    //Métodos


    //Borrar carrito
    public void borrarCarrito(ArrayList productosCarrito){
        productosCarrito.clear();
    }


    public void insertarProducto(Producto producto){
        productosCarrito.add(producto);
    }

    public void elminarProducto(Carrito producto){
        productosCarrito.remove(producto);
    }



    public boolean realizarPagoEfectivo(double montoRecibido) {
        double pago = montoRecibido - subTotal;

        if (pago >= 0) {
            System.out.println("Pago en efectivo recibido");
            generarBoleta();
            return true;
        } else {
            System.out.println("Pago no realizado. Por favor, realice su pago.");
            return false;
        }
    }

    private void generarBoleta() {

       
    }



}
