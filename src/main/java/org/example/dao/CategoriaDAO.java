package org.example.dao;
import org.example.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/multisales";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "a1611";

    private static final String INSERTAR_CATEGORIA = "INSERT INTO categorias (idCategoria, nombre, descripcion) VALUES (?, ?, ?)";
    private static final String OBTENER_CATEGORIAS = "SELECT * FROM categorias";
    private static final String OBTENER_CATEGORIA_POR_ID = "SELECT * FROM categorias WHERE idCategoria = ?";

    public void insertarCategoria(Categoria categoria) {
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/multisales","root", "a1611");
             PreparedStatement statement = conexion.prepareStatement(INSERTAR_CATEGORIA)) {
            statement.setInt(1, categoria.getIdCategoria());
            statement.setString(2, categoria.getNombre());
            statement.setString(3, categoria.getDescripcion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Categoria> obtenerCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/multisales", "root", "a1611");
             PreparedStatement statement = conexion.prepareStatement(OBTENER_CATEGORIAS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idCategoria = resultSet.getInt("idCategoria");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                categorias.add(new Categoria(idCategoria, nombre, descripcion));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return categorias;
    }

    public Categoria obtenerCategoriaPorId(int idCategoria) {
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/multisales", "root", "a1611");
             PreparedStatement statement = conexion.prepareStatement(OBTENER_CATEGORIA_POR_ID)) {
            statement.setInt(1, idCategoria);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    return new Categoria(idCategoria, nombre, descripcion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }




}
