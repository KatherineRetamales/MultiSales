package org.example.dao;
import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.models.Usuario;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoriaDAO {



    public static void addCategoriaToMenu(long id, Object nuevaCategoria) {
    }




    public Categoria findById(Long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Categoria categoria = session.get(Categoria.class, id);
            session.close();
            return categoria;
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

    public void insert(Categoria categoria){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(categoria);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void update(Categoria categoria){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(categoria);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void delete(Categoria categoria) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(categoria);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addProducto(Long categoriaId, Producto producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Obtener la categoría
            Categoria categoria = session.get(Categoria.class, categoriaId);
            if (categoria != null) {
                // Asignar categoría al producto
                categoria.addProducto(producto);
                // Guardar la actualización
                session.saveOrUpdate(producto);
            }

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }


    public void setNombre(String nombreEditado) {
    }

    public void setDescripcion(String descripcionEditada) {
    }
}
