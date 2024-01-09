package org.example;
import org.example.dao.UsuarioDAO;
import org.example.models.Usuario;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 

{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        // Inicio de sesión
        System.out.println("Bienvenido al sistema MultiSales.");
        System.out.print("Ingrese su correo: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese su clave: ");
        String clave = scanner.nextLine();
        Usuario usuarioLgueado = loguearse(usuario,clave);
        if(usuarioLgueado != null){
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuarioLgueado.getNombreUsuario() + "!");
            switch (usuarioLgueado.getRol()) {
                case "admin":
                    menuAdmin(scanner);
                    break;
                case "cliente":
                    menuCliente(scanner);
                    break;
                default:
                    System.out.println("El rol incorrecto");
            }
        } else {
            System.out.println("Credenciales incorrectas. Saliendo del programa.");
        }
    }

    private static Usuario loguearse(String usuario, String clave){
        Usuario usuarioLogueado = new Usuario("Katherine","Retamales","Calle Vitoria 4037","Santiago","kretamales280@gmail.com","123","admin",2);
        return usuarioLogueado;
    }



    private static void menuAdmin(Scanner scanner) {
        while (true) {
            System.out.println("\nUsted está en en menu de Admin. ¿Qué desea hacer?:");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Editar Usuario");
            System.out.println("3. Eliminar Usuario");
            System.out.println("4. Ver Usuarios");
            System.out.println("5. Agregar Producto");
            System.out.println("6. Editar Producto");
            System.out.println("7. Eliminar Producto");
            System.out.println("8. Ver Productos");
            System.out.println("9. Cerrar sesión");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para crear usuario
                    System.out.println("Usuario agregado");
                    break;
                case 2:
                    // Lógica para editar usuario
                    System.out.println("Usuario editado");
                    break;
                case 3:
                    // Lógica para eliminar usuario
                    System.out.println("Usuario eliminado");
                    break;
                case 4:
                    // Lógica para agregar producto
                    System.out.println("Producto agregado");
                    break;
                case 5:
                    // Lógica para ver productos
                    System.out.println("Ver todos los usuarios");
                    break;
                case 56:
                    // Lógica para editar producto
                    System.out.println("Producto editado");

                    break;
                case 6:
                    // Lógica para eliminar producto
                    System.out.println("Producto eliminado");
                    // Lógica para realizar tarea
                    break;
                case 7:
                    System.out.println("Cerrando sesión. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }

    }

    private static void menuCliente(Scanner scanner) {
    }

}
