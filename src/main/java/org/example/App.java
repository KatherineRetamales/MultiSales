package org.example;

import org.example.dao.*;

import org.example.models.*;

import org.example.dao.CategoriaDAO;

import org.example.dao.PedidoDAO;
import org.example.dao.ProductoDAO;
import org.example.dao.UsuarioDAO;
import org.example.models.Categoria;
import org.example.models.Pedido;
import org.example.models.Producto;
import org.example.models.Usuario;


import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuLogearse(scanner);
    }

    private static void menuLogearse(Scanner scanner) {
        // Inicio de sesión
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         MultiSales           ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese su clave: ");
        String clave = scanner.nextLine();

        Usuario usuarioLogueado = loguearse(correo, clave);

        if (usuarioLogueado != null) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenid@, " + usuarioLogueado.getNombreUsuario() );
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
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        while (true) {
            System.out.println("\nUsted está en en menu de Admin. ¿Qué desea hacer?:");
            System.out.println("========================");
            System.out.println("_______USUARIO________");
            System.out.println("========================");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Editar Usuario");
            System.out.println("3. Eliminar Usuario");
            System.out.println("4. Ver Usuarios");
            System.out.println("========================");
            System.out.println("_____PRODUCTOS________");
            System.out.println("========================");
            System.out.println("5. Agregar Producto");
            System.out.println("6. Editar Producto");
            System.out.println("7. Eliminar Producto");
            System.out.println("8. Ver Productos");
            System.out.println("========================");
            System.out.println("_______CATEGORIA________");
            System.out.println("========================");
            System.out.println("9. Agregar Categoría");
            System.out.println("10. Editar Categoría");
            System.out.println("11. Eliminar Categoría");
            System.out.println("12. Ver Categorías");
            System.out.println("========================");
            System.out.println("_______SESIÓN________");
            System.out.println("========================");
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

                    mostrarUsuarios();
                    break;
                case 5:
                    // Lógica para agregar producto
                    agregarProducto(scanner);
                    System.out.println("Producto agregado");
                    break;
                case 6:
                    // Lógica para editar producto
                    editarProductos(scanner);
                    System.out.println("Producto editado");
                    break;
                case 7:
                    // Lógica para eliminar producto
                    eliminarProducto(scanner);
                    System.out.println("Producto eliminado");
                    break;
                case 8:
                    // Lógica para ver todos los productos
                    mostrarProducto();
                    System.out.println("Ver todos los Producto");
                    break;

                case 9:

                    // Lógica para agregar categoria
                    System.out.println("Ingresa el nombre de la categoria");
                    String nombreCategoria = scanner.next();

                    System.out.println("Ingresa la descripcion de la categoria");
                    String descripcionCategoria = scanner.next();

                    Categoria nuevaCategoria = new Categoria(nombreCategoria, descripcionCategoria);

                    categoriaDAO.insert(nuevaCategoria);


                    break;
                case 10:
                    // Lógica para editar categoria
                    Categoria categoria = new Categoria();
                    categoria.editarCategoria(scanner);

                    break;
                case 11:
                    // Lógica para eliminar categoria
                    eliminarCategoria(scanner);
                    break;
                case 12:
                    // Lógica para ver todas las categorias
                    categoriaDAO = new CategoriaDAO();
                    Categoria.verCategorias(scanner, categoriaDAO);
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
            usuarioDAO.update(editUsuario);
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
//Mostrar Usuarios
private static void mostrarUsuarios() {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> usuarios = usuarioDAO.findAll();

    if (usuarios != null && !usuarios.isEmpty()) {
        System.out.println("\nLista de usuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombreUsuario());
            System.out.println("Apellido: " + usuario.getApellidoUsuario());
            System.out.println("Correo: " + usuario.getEmail());
            System.out.println("Rol: " + usuario.getRol());
            System.out.println("------------------------------");
        }
    } else {
        System.out.println("No se encontraron usuarios.");
    }
}
    private static void menuCliente(Scanner scanner, Long idUsuario) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Carrito carrito = new Carrito();

        Usuario usuario = new Usuario();
        while (true) {
            System.out.println("\nUsted está en el menu de Cliente. ¿Qué desea hacer?");
            System.out.println("1. Ver productos.");
            System.out.println("2. Agregar producto.");
            System.out.println("3. Ver carrito de compras.");
            System.out.println("4. Eliminar producto.");
            System.out.println("5. Pagar.");
            System.out.println("6. Ver mis compras");
            System.out.println("7. Editar datos personales");
            System.out.println("8. Cerrar sesión");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para ver productos
                    listarProductos(productoDAO);
                    break;
                case 2:
                    // Lógica para agregar producto al carrito de compras
                    System.out.print("Ingrese el ID del producto que quiere agregar: ");
                    Long idProductoAgregar = scanner.nextLong();
                    Producto productoAgregar = productoDAO.findById(idProductoAgregar);
                    carrito.agregarProducto(productoAgregar);
                    break;
                case 3:
                    // Lógica para mostrar carrito de compras
                    carrito.mostrarCarrito();
                    break;
                case 4:
                    // Lógica para eliminar un producto del carrito de compras
                    System.out.print("Ingrese el ID del producto a eliminar del carrito: ");
                    Long idProductoEliminar = scanner.nextLong();
                    Producto productoEliminar = productoDAO.findById(idProductoEliminar);
                    carrito.eliminarProducto(productoEliminar);
                    break;
                case 5:
                    // Lógica para pagar lo que esta en el carrito de compras
                    // Crear un nuevo pedido
                    Pedido nuevoPedido = new Pedido("Efectivo", 2, carrito.getProductos().size(), new Date(), carrito.getTotal());
                    System.out.print("Metodo pago" + nuevoPedido.getMetodoPago());
                    System.out.print("Cantidad" + nuevoPedido.getCantidadProducto());
                    System.out.print("Total" + nuevoPedido.getTotalCompra());

                    usuarioDAO.addPedido(idUsuario, nuevoPedido);

                    // Iterar sobre los productos en el carrito
                    for (Producto producto : carrito.getProductos()) {
                        // Crear una relación PedidoProducto
                        Pedido_Producto pedido_producto = new Pedido_Producto();
                        // Añadir la relación PedidoProducto a la base de datos
                        pedidoDAO.addPedidoProducto(nuevoPedido.getIdPedido(), pedido_producto);

                        // Añadir la relación PedidoProducto al Producto
                        productoDAO.addPedidoProducto(producto.getIdProducto(), pedido_producto);

                    }
                    break;
                case 6:
                    // Lógica para ver compras
                    verComprasUsuario(idUsuario, pedidoDAO);
                    break;
                case 7:
                    // Lógica para editar perfil
                    editarDatosPersonales(scanner, idUsuario);
                    break;
                case 8:
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
                //  pagarProductos();
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
                Pedido_ProductoDAO pedidoProductoDAO = new Pedido_ProductoDAO();
                List<Pedido_Producto> pedido_productos = pedidoProductoDAO.findByIdPedido(pedido.getIdPedido());
                if (pedido_productos.isEmpty()) {
                    System.out.println("No hay pedidos.");
                } else {
                    System.out.println("Lista de productos por pedido:");
                    for (Pedido_Producto pedidoProducto : pedido_productos) {
                        System.out.println("ID Producto: " + pedidoProducto.getProducto().getIdProducto());
                        System.out.println("Producto: " + pedidoProducto.getProducto().getNombreProducto());
                        System.out.println("Precio: " + pedidoProducto.getProducto().getPrecio());

                        System.out.println("------------------------------");

                    }
                }
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

    public static void agregarProducto (Scanner scanner) {

        System.out.println("Ingresa el nombre del Producto");
        String nombreDelProducto = scanner.next();

        System.out.println("Ingresa la cantidad del Producto");
        int cantidadDelProducto = scanner.nextInt();

        System.out.println("Ingresa una descripcion del Producto");
        String descripcionDelProducto = scanner.next();

        System.out.println("Ingresa el precio del Producto");
        double precioDelProducto = scanner.nextDouble();

        System.out.println("Ingresa el ID de la Categoría");
        Long idCategoria = scanner.nextLong();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Producto nuevoProducto = new Producto(nombreDelProducto,cantidadDelProducto,descripcionDelProducto,precioDelProducto);
        categoriaDAO.addProducto(idCategoria, nuevoProducto);

    }
    private static void editarProductos(Scanner scanner) {
        System.out.println("Ingresa el ID del producto a editar: ");
        Long idProducto = scanner.nextLong();
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = productoDAO.findById(idProducto);
        System.out.println("¿Que dato del producto desea editar?");
        System.out.println("1. Editar nombre");
        System.out.println("2. Editar el stock");
        System.out.println("3. Editar la descripcion");
        System.out.println("4. Editar el precio");
        System.out.println("5. Ver productos");
        System.out.println("6. Volver");
        System.out.print("Seleccione una opción: ");

        // Leer la opción del producto
        int opcion = scanner.nextInt();

        // Realizar la acción correspondiente
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre del producto: ");
                String nuevoNombreProducto = scanner.next();
                producto.setNombreProducto(nuevoNombreProducto);
                productoDAO.update(producto);
                break;
            case 2:
                System.out.print("Ingrese el nuevo Stock del producto: ");
                int nuevoStockProducto = scanner.nextInt();
                producto.setStock(nuevoStockProducto);
                productoDAO.update(producto);
                break;
            case 3:
                System.out.print("Ingrese la nueva descripcion del producto: ");
                String nuevaDescripcionProducto = scanner.next();
                producto.setDescripcionProducto(nuevaDescripcionProducto);
                productoDAO.update(producto);
                break;
            case 4:
                System.out.print("Ingrese el nuevo precio del producto: ");
                double nuevaPrecioProducto = scanner.nextInt();
                producto.setPrecio(nuevaPrecioProducto);
                productoDAO.update(producto);
                break;
            case 5:
                System.out.println("Información del producto actualizada:");
                System.out.println("Nombre: " + producto.getNombreProducto());
                System.out.println("Stock: " + producto.getStock());
                System.out.println("Descripcion: " + producto.getDescripcionProducto());
                System.out.println("Precio: " + producto.getPrecio());
                break;
            case 6:
                menuAdmin(scanner);
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }
    public static void eliminarProducto (Scanner scanner){
        System.out.println("Ingresa el ID del producto a eliminar: ");
        Long idProducto = scanner.nextLong();
        ProductoDAO productoDAO = new ProductoDAO();
        Producto productoAEliminar = productoDAO.findById(idProducto);
        if (productoAEliminar != null) {
            productoDAO.delete(productoAEliminar);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }

    }
    private static void mostrarProducto() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.findAll();

        if (productos != null && !productos.isEmpty()) {
            System.out.println("\nLista de productos:");
            for (Producto producto : productos) {
                System.out.println("ID: " + producto.getIdProducto());
                System.out.println("Nombre: " + producto.getNombreProducto());
                System.out.println("Stock: " + producto.getStock());
                System.out.println("Descripcion: " + producto.getDescripcionProducto());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No se encontraron productos.");
        }
    }

    public static void listarProductos(ProductoDAO productoDAO){
        try {
            List<Producto> productos = productoDAO.findAll();
            if (productos.isEmpty()) {
                System.out.println("No hay productos.");
            } else {
                // Imprime todos los productos
                System.out.println("Lista de productos:");
                for (Producto producto : productos) {
                    System.out.println("ID: " + producto.getIdProducto());
                    System.out.println("Producto: " + producto.getNombreProducto());
                    System.out.println("Descripcion: " + producto.getDescripcionProducto());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Stock: " + producto.getStock());
                    System.out.println("Categoria: " + producto.getCategoria().getNombre());
                    System.out.println("------------------------------");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void   eliminarCategoria (Scanner scanner) {
        System.out.println("Ingresa el ID de la categoría a eliminar: ");
        Long idCategoria = scanner.nextLong();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoriaAEliminar = categoriaDAO.findById(idCategoria);
        if (categoriaAEliminar != null) {
            categoriaDAO.delete(categoriaAEliminar);
            System.out.println("Categoría eliminado correctamente.");
        } else {
            System.out.println("No se encontró una categoría con ese ID.");
        }
    }



}