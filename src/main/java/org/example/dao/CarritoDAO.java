package org.example.dao;

import org.example.models.Carrito;
import org.example.models.Usuario;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class CarritoDAO {


    public List<Carrito> findAll() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Carrito").list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public void insert(Carrito Carroproducto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(Carroproducto);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(Carrito Carroproducto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Carroproducto);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Carrito Carroproducto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(Carroproducto);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




}
