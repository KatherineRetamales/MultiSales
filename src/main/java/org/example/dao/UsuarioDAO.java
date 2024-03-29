package org.example.dao;


import org.example.models.Categoria;
import org.example.models.Pedido;
import org.example.models.Producto;
import org.example.models.Usuario;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> findByCorreoAndClave(String email){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Usuario WHERE email='"+email+"'").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Usuario findByCorreo(String email, String clave){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Usuario WHERE email= :correo AND contrasena= :clave";

            // Crear la consulta HQL
            Query query = session.createQuery(hql, Usuario.class);
            query.setParameter("correo", email);
            query.setParameter("clave", clave);

            // Obtener el resultado de la consulta
            Usuario usuario = (Usuario) ((org.hibernate.query.Query<?>) query).uniqueResult();
            session.close();
            return usuario;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Usuario findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Usuario usuario = session.get(Usuario.class, id);
            session.close();
            return usuario;
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
    public void insert(Usuario usuario) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(Usuario usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(usuario);
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

    public void addPedido(Long usuarioId, Pedido pedido) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Obtener usuario
            Usuario usuario = session.get(Usuario.class, usuarioId);
            if (usuario != null) {
                // Asignar pedido a usuario
                usuario.addPedido(pedido);
                // Guardar la actualización
                session.saveOrUpdate(pedido);
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
