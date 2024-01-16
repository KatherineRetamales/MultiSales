package org.example.dao;

import org.example.models.Producto;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductoDAO {


    public Producto findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Producto.class, 1l);
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


}
