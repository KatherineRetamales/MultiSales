package org.example.dao;

import org.example.models.Pedido;
import org.example.models.Pedido_Producto;
import org.example.models.Producto;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductoDAO {


    public Producto findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Producto.class, id);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Producto> findAll(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Producto").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Producto> findByName(String name){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Producto WHERE nombreProducto='"+name+"'").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Producto> findByCategoria(int id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Producto WHERE categria_id='"+id+"'").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public  void insert(Producto producto){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){;
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
        } catch (Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
    public void update(Producto producto){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(producto);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void delete(Producto producto){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(producto);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addPedidoProducto(Long productoId, Pedido_Producto pedido_producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Obtener el producto
            Producto producto = session.get(Producto.class, productoId);
            if (producto != null) {
                // Asignar idProducto al pedido producto
                producto.addProductoProducto(pedido_producto);
                // Guardar la actualización
                session.saveOrUpdate(pedido_producto);
            }

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }


}
