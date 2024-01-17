package org.example.dao;

import org.example.models.*;
import org.hibernate.Session;
import java.util.List;
import org.example.util.HibernateUtil;
import org.hibernate.Transaction;


public class PedidoDAO {
    public List<Pedido> findByIdUsuario(Long id_usuario){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Pedido P JOIN FETCH P.pedido_producto PP WHERE idUsuario='"+id_usuario+"' GROUP BY PP.idPedido").list();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(Pedido pedido) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(pedido);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addPedidoProducto(Long pedidoId, Pedido_Producto pedido_producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Obtener el pedido
            Pedido pedido = session.get(Pedido.class, pedidoId);
            if (pedido != null) {
                // Asignar idPedido al pedido producto
                pedido.addProductoProducto(pedido_producto);
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
};
