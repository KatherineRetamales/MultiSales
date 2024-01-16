package org.example;
import org.example.dao.CategoriaDAO;

import org.example.dao.PedidoDAO;
import org.example.dao.ProductoDAO;
import org.example.dao.UsuarioDAO;
import org.example.models.Categoria;
import org.example.models.Pedido;
import org.example.models.Producto;
import org.example.models.Usuario;



import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuLogearse(scanner);
    }

    private static void menuLogearse(Scanner scanner) {
        // Inicio de sesión
        System.out.println("Bienvenido al sistema MultiSales.");
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese su clave: ");
        String clave = scanner.nextLine();

        Usuario usuarioLogueado = loguearse(correo, clave);

        if (usuarioLogueado != null) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuarioLogueado.getNombreUsuario() + "!");
            switch (usuarioLogueado.getRol()) {
                case "admin":
                    menuAdmin(scanner);
                    break;
                case "cliente":
                    menuCliente(scanner, usuarioLogueado.getIdUsuario());
                    break;
                default:
                    System.out.println("El rol incorrecto");
            }
        } else {
            System.out.println("Credenciales incorrectas.");
            menuLogearse(scanner);
        }
    }

    private static Usuario loguearse(String correo, String clave) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.findByCorreo(correo, clave);
        return usuario;
    }

    private static void menuAdmin(Scanner scanner) {
       ProductoDAO productoDAO = new ProductoDAO();

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

                    agregarUsuario(scanner);
                    break;
                case 2:

                    editarUsuario(scanner);
                    break;
                case 3:
                    eliminarUsuario(scanner);
                    break;
                case 4:
                    // Lógica para ver todos los usuarios
                    System.out.println("Ver todos los usuarios");
                    break;
                case 5:


                    // Lógica para agregar producto
                    agregarProducto(scanner,productoDAO);
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
                    System.out.println("\n¿Desea agregar una categoria?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    Categoria nuevaCategoria = new Categoria();
                    Categoria.agregarCategoria();


                    break;
                case 10:
                    // Lógica para editar categoria
                    System.out.println("\n¿Desea editar una categoria?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    Categoria.editarCategoria();

                    break;
                case 11:
                    // Lógica para eliminar categoria
                    System.out.println("\n¿Desea eliminar una categoria?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    Categoria.deleteCategoria();

                    break;

                case 12:
                    // Lógica para ver todas las categorias
                    System.out.println("\n¿Desea ver todas las categorias?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    Categoria.verTodasCategorias();
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

    private static void agregarUsuario(Scanner scanner) {
        String nombre = ObtenerInfoUsuario("Ingrese el nombre del usuario: ", scanner);
        String apellido = ObtenerInfoUsuario("Ingrese el apellido del usuario: ", scanner);
        String direccion = ObtenerInfoUsuario("Ingrese la dirección del usuario: ", scanner);
        String ciudad = ObtenerInfoUsuario("Ingrese la ciudad del usuario: ", scanner);
        String email = ObtenerInfoUsuario("Ingrese el correo electrónico del usuario: ", scanner);
        String contrasena = ObtenerInfoUsuario("Ingrese la contraseña del usuario: ", scanner);
        String rol = ObtenerInfoUsuario("Ingrese el rol del usuario: ", scanner);
        int numCelular = ObtenerNumUsuario("Ingrese el número de celular del usuario: ", scanner);

        Usuario nuevoUsuario = new Usuario(nombre, apellido, direccion, ciudad, email, contrasena, rol, numCelular);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insert(nuevoUsuario);

        System.out.println("Usuario agregado con éxito.");
    }

    private static String ObtenerInfoUsuario(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        scanner.nextLine();
        return scanner.next();
    }

    private static int ObtenerNumUsuario(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        scanner.nextLine();
        return scanner.nextInt();

    }

    //EDITAR USUARIO
    private static void editarUsuario(Scanner scanner) {
        System.out.println("Ingresa el ID del usuario a editar: ");
        Long idUsuario = scanner.nextLong();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario editUsuario = usuarioDAO.findById(idUsuario);

        if (editUsuario != null) {
            System.out.println("Elige la opción a editar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Dirección");
            System.out.println("4. Ciudad");
            System.out.println("5. Contraseña");
            System.out.println("6. Rol");
            System.out.println("7. Celular");
            int opcionIngresada = scanner.nextInt();

            switch (opcionIngresada) {
                case 1:
                    System.out.println("Ingresa el nuevo nombre");
                    String nombreEditado = scanner.next();
                    editUsuario.setNombreUsuario(nombreEditado);
                    break;
                case 2:
                    System.out.print("Ingrese el apellido: ");
                    String nuevoApellido = scanner.next();
                    editUsuario.setApellidoUsuario(nuevoApellido);
                    break;
                case 3:
                    System.out.print("Ingrese la dirección: ");
                    String nuevaDireccion = scanner.next();
                    editUsuario.setDireccion(nuevaDireccion);
                    break;
                case 4:
                    System.out.print("Ingrese ciudad: ");
                    String nuevaCiudad = scanner.next();
                    editUsuario.setCiudad(nuevaCiudad);
                    break;
                case 5:
                    System.out.print("Ingrese la nueva contraseña: ");
                    String nuevaContrasena = scanner.next();
                    editUsuario.setContrasena(nuevaContrasena);
                    break;
                case 6:
                    System.out.print("Ingrese el nuevo rol: ");
                    String nuevoRol = scanner.next();
                    editUsuario.setRol(nuevoRol);
                    break;
                case 7:
                    System.out.println("Ingresa el num de celular: ");
                    int nuevoNumCelular = scanner.nextInt();
                    editUsuario.setNumCelular(nuevoNumCelular);
                    break;
                default:
                    System.out.println("Opcion invalida (1 - 7)");

            }
            UsuarioDAO.update(editUsuario);
            System.out.println("Usuario editado correctamente! ");
        } System.out.println("Usuario no encontrado");
    }

    public static void eliminarUsuario (Scanner scanner){
        System.out.println("Ingresa el ID del usuario a eliminar: ");
        Long idUsuario = scanner.nextLong();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioAEliminar = usuarioDAO.findById(idUsuario);
        if (usuarioAEliminar != null) {
            usuarioDAO.delete(usuarioAEliminar);
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No se encontró un usuario con ese ID.");
        }

    }

    private static void menuCliente(Scanner scanner, Long idUsuario) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        while (true) {
            System.out.println("\nUsted está en el menu de Cliente. ¿Qué desea hacer?");
            System.out.println("1. Ver productos.");
            System.out.println("2. Ver carrito de compras");
            System.out.println("3. Ver mis compras");
            System.out.println("4. Datos personales");
            System.out.println("5. Cerrar sesión");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para ver productos
                    menuVerproductos(scanner, idUsuario);
                    break;
                case 2:
                    // Lógica para ver carrito de compras
                    menuCarritoCompras(scanner, idUsuario);
                    break;
                case 3:
                    // Lógica para ver compras
                    verComprasUsuario(idUsuario, pedidoDAO);
                    break;
                case 4:
                    // Lógica para editar perfil
                    editarDatosPersonales(scanner, idUsuario);
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

    private static void menuVerproductos(Scanner scanner, Long idUsuario){
        while (true) {
            System.out.println("\nProductos:");
            System.out.println("1. Agregar al carrito de compras.");
            System.out.println("2. Ver carrito de compras");
            System.out.println("3. Filtrar productos por categoría.");
            System.out.println("4. Volver al menu principal");;

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para agregar al carrito de compras
                    break;
                case 2:
                    menuCarritoCompras(scanner, idUsuario);
                    break;
                case 3:
                    // Lógica para volver al menu principal
                    menuCliente(scanner, idUsuario);
                    break;
                case 4:
                    // Lógica para filtrar productos por categoria
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    private static void menuCarritoCompras(Scanner scanner, Long idUsuario){
        while (true) {
            System.out.println("\nCarrito de compras:");
            System.out.println("1. Eliminar producto.");
            System.out.println("2. Pagar");
            System.out.println("3. Volver al menu principal");

            int opcion = scanner.nextInt();

            Pedido p1 = new Pedido();

            switch (opcion) {
                case 1:
                    // Lógica para eliminar producto del carrito de compras
                    break;
                case 2:
                    //Logica para pagar
                    // Realizar el pago en efectivo
                    /*NO FUNCIONA
                    if (Pedido.realizarPago()) {
                        // Generar la boleta si el pago fue exitoso
                       Pedido.generarBoleta();
                    }

                     */
                    break;
                case 3:
                    // Lógica para volver al menu principal
                    menuCliente(scanner, idUsuario);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }


    private static void verComprasUsuario(Long idUsuario, PedidoDAO pedidoDAO){
        List<Pedido> pedidos = pedidoDAO.findByIdUsuario(idUsuario);

        if (pedidos.isEmpty()) {
            System.out.println("No tienes compras realizadas.");
        } else {
            System.out.println("Lista de compras:");
            for (Pedido pedido : pedidos) {
                System.out.println("ID: " + pedido.getIdPedido());
                System.out.println("Fecha compra: " + pedido.getFechaCompra());
                System.out.println("Cantidad de productos: " + pedido.getCantidadProducto());
                System.out.println("Total compra: " + pedido.getTotalCompra());
                System.out.println("Método de pago: " + pedido.getMetodoPago());
                System.out.println("------------------------------");
            }
        }
    }

    private static void editarDatosPersonales(Scanner scanner, Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.findById(idUsuario);
        System.out.println("¿Que datos desea editar?");
        System.out.println("1. Editar nombre");
        System.out.println("2. Editar apellido");
        System.out.println("3. Editar dirección");
        System.out.println("4. Editar ciudad");
        System.out.println("5. Editar correo");
        System.out.println("6. Editar telefono");
        System.out.println("7. Ver sus datos personales");
        System.out.println("8. Volver");
        System.out.print("Seleccione una opción: ");

        // Leer la opción del usuario
        int opcion = scanner.nextInt();

        // Realizar la acción correspondiente
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre: ");
                String nuevoNombre = scanner.next();
                usuario.setNombreUsuario(nuevoNombre);
                usuarioDAO.update(usuario);
                break;
            case 2:
                System.out.print("Ingrese el nuevo apellido: ");
                String nuevoApellido = scanner.next();
                usuario.setApellidoUsuario(nuevoApellido);
                usuarioDAO.update(usuario);
                break;
            case 3:
                System.out.print("Ingrese la nueva dirección: ");
                String nuevaDireccion = scanner.next();
                usuario.setDireccion(nuevaDireccion);
                usuarioDAO.update(usuario);
                break;
            case 4:
                System.out.print("Ingrese la nueva ciudad: ");
                String nuevaCiudad = scanner.next();
                usuario.setDireccion(nuevaCiudad);
                usuarioDAO.update(usuario);
                break;
            case 5:
                System.out.print("Ingrese el nuevo correo: ");
                String nuevoCorreo = scanner.next();
                usuario.setDireccion(nuevoCorreo);
                usuarioDAO.update(usuario);
                break;
            case 6:
                System.out.print("Ingrese el nuevo teléfono: ");
                String nuevoTelefono = scanner.next();
                usuario.setDireccion(nuevoTelefono);
                usuarioDAO.update(usuario);
                break;
            case 7:
                System.out.println("Información del usuario:");
                System.out.println("Nombre: " + usuario.getNombreUsuario());
                System.out.println("Apellido: " + usuario.getApellidoUsuario());
                System.out.println("Dirección: " + usuario.getDireccion());
                System.out.println("Ciudad: " + usuario.getCiudad());
                System.out.println("Correo: " + usuario.getEmail());
                System.out.println("Teléfono: " + usuario.getNumCelular());
                break;
            case 8:
                menuCliente(scanner, idUsuario);
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }
    public static void agregarProducto (Scanner scanner, ProductoDAO productoDAO) {

        System.out.println("Ingresa el nombre del Producto");
        String nombreDelProducto = scanner.next();
        Producto productoNuevo = new Producto();
        productoNuevo.setNombreProducto(nombreDelProducto);

        System.out.println("Ingresa la cantidad del Producto");
        int cantidadDelProducto = scanner.nextInt();
        Producto cantidadDeProductosIngresados = new Producto();
        cantidadDeProductosIngresados.setStock(cantidadDelProducto);

        System.out.println("Ingresa una descripcion del Producto");
        String descripcionDelProducto = scanner.next();
        Producto descripcionDelProductoIngresado = new Producto();
        descripcionDelProductoIngresado.setDescripcionProducto(descripcionDelProducto);

        System.out.println("Ingresa el precio del Producto");
        double precioDelProducto = scanner.nextInt();
        Producto precioDelProductoIngresado = new Producto();
        precioDelProductoIngresado.setPrecio(precioDelProducto);


        Producto producto = new Producto(nombreDelProducto, cantidadDelProducto, descripcionDelProducto, precioDelProducto);
        productoDAO.insert(producto);


    }


}