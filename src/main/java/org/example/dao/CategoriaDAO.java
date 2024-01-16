package org.example.dao;
import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoriaDAO {


    public static void addCategoriaToMenu(long id, Object nuevaCategoria) {
    }

    public static Categoria findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Categoria.class, 1l);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Categoria> findAll(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Categoria").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Categoria> findByName(String name){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Categoria WHERE nombreCategoria='"+name+"'").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static void insert(Categoria categoria){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(categoria);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void update(Categoria categoria){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(categoria);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void delete(Categoria categoria) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(categoria);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
