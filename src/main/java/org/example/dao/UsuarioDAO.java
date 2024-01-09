package org.example.dao;


import org.example.models.Usuario;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {


    public Usuario findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Usuario.class, 1l);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Usuario> findAll(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Usuario").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<Usuario> findByName(String name){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Usuario WHERE firstName='"+name+"'").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public  void insert(Usuario Usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(Usuario);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void update(Usuario Usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Usuario);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void delete(Usuario Usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(Usuario);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
