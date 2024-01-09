package org.example.dao;

import org.example.models.Pedido;
import org.hibernate.Session;
import java.util.List;
import org.example.util.HibernateUtil;



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
}
;
