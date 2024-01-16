package org.example.util;

import org.example.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    //SessionFactory es una clase de Hibernate para guardar la conexion
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Propiedades de conexio de Hibernate
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/carrito_compras");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Juanito_2023$");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //Manejar la construccion de tablas de la base de datos
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                //Asociar las clases
                configuration.addAnnotatedClass(Pedido.class);
                configuration.addAnnotatedClass(Pedido_Producto.class);
                configuration.addAnnotatedClass(Producto.class);
                configuration.addAnnotatedClass(Usuario.class);
                configuration.addAnnotatedClass(Categoria.class);
                //Servicio de parametros de conexion
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                //Crear conexion. Será utilizada por los DAO's
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
