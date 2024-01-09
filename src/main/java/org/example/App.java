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
        menuLogearse(scanner);
    }

   private static void menuLogearse(Scanner scanner){
       // Inicio de sesión
       System.out.println("Bienvenido al sistema MultiSales.");
       System.out.print("Ingrese su correo: ");
       String correo = scanner.nextLine();

       System.out.print("Ingrese su clave: ");
       String clave = scanner.nextLine();
       Usuario usuarioLgueado = loguearse(correo,clave);
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
        Usuario usuarioLogueado = new Usuario("Katherine","Retamales","Calle Vitoria 4037","Santiago","kretamales280@gmail.com","123","cliente",2);
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
            System.out.println("9. Agregar Categoría");
            System.out.println("10. Editar Categoría");
            System.out.println("11. Eliminar Categoría");
            System.out.println("12. Ver Categorías");
            System.out.println("13. Cerrar sesión");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para agregar usuario
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
                    // Lógica para ver todos los usuarios
                    System.out.println("Ver todos los usuarios");
                    break;
                case 5:
                    // Lógica para agregar producto
                    System.out.println("Producto agregado");
                    break;
                case 6:
                    // Lógica para editar producto
                    System.out.println("Producto editado");
                    break;
                case 7:
                    // Lógica para eliminar producto
                    System.out.println("Producto eliminado");
                    break;
                case 8:
                    // Lógica para ver todos los productos
                    System.out.println("Producto eliminado");
                    break;
                case 9:
                    // Lógica para agregar categoria
                    System.out.println("Categoria agregada");
                    break;
                case 10:
                    // Lógica para editar categoria
                    System.out.println("Categoria editada");
                    break;
                case 11:
                    // Lógica para eliminar categoria
                    System.out.println("Categoria eliminada");
                    break;
                case 12:
                    // Lógica para ver todos los productos
                    System.out.println("Producto eliminado");
                    break;
                case 13:
                    System.out.println("Cerrando sesión. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }

    }

    private static void menuCliente(Scanner scanner) {
        while (true) {
            System.out.println("\nUsted está en en menu de Cliente. ¿Qué desea hacer?:");
            System.out.println("1. Ver productos.");
            System.out.println("2. Ver carrito de compras");
            System.out.println("3. Ver compras");
            System.out.println("4. Editar perfil");
            System.out.println("5. Cerrar sesión");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para ver productos
                    menuVerproductos(scanner);
                    break;
                case 2:
                    // Lógica para ver carrito de compras
                    menuCarritoCompras(scanner);
                    break;
                case 3:
                    // Lógica para ver compras
                    break;
                case 4:
                    // Lógica para editar perfil
                    break;
                case 5:
                    System.out.println("Cerrando sesión. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    private static void menuVerproductos(Scanner scanner){
        while (true) {
            System.out.println("\nProductos:");
            System.out.println("1. Agregar al carrito de compras.");
            System.out.println("2. Ver carrito de compras");
            System.out.println("3. Volver al menu principal");;

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para agregar al carrito de compras
                    break;
                case 2:
                    menuCarritoCompras(scanner);
                    break;
                case 3:
                    // Lógica para volver al menu principal
                    menuCliente(scanner);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    private static void menuCarritoCompras(Scanner scanner){
        while (true) {
            System.out.println("\nCarrito de compras:");
            System.out.println("1. Eliminar producto.");
            System.out.println("2. Pagar");
            System.out.println("3. Volver al menu principal");;

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para eliminar producto del carrito de compras
                    break;
                case 2:
                    //Logica para pagar
                    break;
                case 3:
                    // Lógica para volver al menu principal
                    menuCliente(scanner);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}
