package org.example;

import org.example.dao.UsuarioDAO;
import org.example.models.Usuario;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );



    UsuarioDAO usuarioDAO = new UsuarioDAO();

    Usuario nuevoUsuario = new Usuario("Priscila", "Sagua", "PA A 37", "Temuco", "priscila@email.com", "contrasena", "admin", 93000000);

    usuarioDAO.insert(nuevoUsuario);
    }

}
