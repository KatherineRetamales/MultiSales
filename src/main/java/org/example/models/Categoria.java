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

    public void editarCategoria(Scanner scanner) {
        System.out.println("Ingresa el ID de la Categoria a editar: ");
        Long idCategoria = scanner.nextLong();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        CategoriaDAO editarCategoria = categoriaDAO;

        if (editarCategoria != null) {
            System.out.println("Elige la opción a editar");
            System.out.println("1. Nombre");
            System.out.println("2. Descripción");
            int opcionIngresada = scanner.nextInt();

            switch (opcionIngresada) {
                case 1:
                    System.out.println("Ingresa el nuevo nombre");
                    String nombreEditado = scanner.next();
                    editarCategoria.setNombre(nombreEditado);
                    break;
                case 2:
                    System.out.println("Ingresa la nueva descripción");
                    String descripcionEditada = scanner.next();
                    editarCategoria.setDescripcion(descripcionEditada);

                default:
                    System.out.println("Opcion invalida (1 - 2)");

            }
            CategoriaDAO.update(editarCategoria);
            System.out.println("Categoria editada correctamente! ");
        } System.out.println("Categoria no encontrada");
    }

    //VER CATEGORIAS

    public static void verCategorias(Scanner  scanner, CategoriaDAO categoriaDAO){
        try {
            List<Categoria> categorias = categoriaDAO.findAll();
            if (categorias.isEmpty()) {
                System.out.println("No hay categorias.");
            } else {
                // Imprime todas las categorias
                System.out.println("Lista de categorias:");
                for (Categoria categoria : categorias) {
                    System.out.println("ID: " + categoria.getIdCategoria());
                    System.out.println("Categoria: " + categoria.getNombre());
                    System.out.println("Descripcion: " + categoria.getDescripcion());
                    System.out.println("------------------------------");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

