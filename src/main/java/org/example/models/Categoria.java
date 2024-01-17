package org.example.models;

import org.example.dao.CategoriaDAO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long idCategoria;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();

    public Categoria() {
    }

    public static void editarCategoria() {
    }


    public static void deleteCategoria() {
    }

    public static void agregarCategoria() {
    }

    public static void verTodasCategorias() {
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Categoria(String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }


    public void setCategoria() {
    }

    public void setName() {
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
        producto.setCategoria(this);
    }

    public  void deleteCategoria (Scanner scanner){
        System.out.println("Ingresa el ID de la Categoria a eliminar: ");
        Long idCategoria = scanner.nextLong();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoriaAEliminar = categoriaDAO.findById(idCategoria);
        if (categoriaAEliminar != null) {
            categoriaDAO.delete(categoriaAEliminar);
            System.out.println("Categoria eliminada correctamente.");
        } else {
            System.out.println("No se encontró una categoria con ese ID.");
        }

    }



}

