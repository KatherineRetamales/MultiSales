package org.example.dao;

import org.example.models.Pedido;
import org.example.models.Pedido_Producto;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Pedido_ProductoDAO {

    public Pedido_ProductoDAO findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pedido_ProductoDAO.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Pedido_ProductoDAO> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido_ProductoDAO", Pedido_ProductoDAO.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public void insert(Pedido_ProductoDAO pedido_productoDAO) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(pedido_productoDAO);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(Pedido_ProductoDAO pedido_productoDAO) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(pedido_productoDAO);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(Pedido_ProductoDAO pedido_productoDAO) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(pedido_productoDAO);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public List<Pedido_Producto> findByIdPedido(Long idPedido){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Pedido_Producto WHERE idPedido='"+idPedido+"' ").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
