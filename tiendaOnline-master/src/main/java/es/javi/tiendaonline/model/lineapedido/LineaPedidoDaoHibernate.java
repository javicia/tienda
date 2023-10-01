/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.lineapedido;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emilio
 */
@Repository("LineaPedidoDao")
public class LineaPedidoDaoHibernate  extends
		GenericDaoHibernate<LineaPedido, Long> implements LineaPedidoDao{
      @Autowired
      private SessionFactory sessionFactory;
    
    
     public LineaPedido findByIdLineaPedido(int idLineaPedido) throws InstanceNotFoundException {

    	LineaPedido lineaPedido = (LineaPedido) getSession().createQuery(
    			"SELECT u FROM LineaPedido u WHERE u.idLineaPedido = :idLineaPedido")
    			.setParameter("idLineaPedido", idLineaPedido)
    			.uniqueResult();
    	if (lineaPedido == null) {
   			throw new InstanceNotFoundException(idLineaPedido, LineaPedido.class.getName());
    	} else {
    		return lineaPedido;
    	}

	}
     
     
     public void anadirLineaPedido (LineaPedido lineaPedido) {
        sessionFactory.getCurrentSession().save(lineaPedido);
     
     }
     
      public List<LineaPedido> ListarLineasPedido (long idPedido) {
        
         Query query = getSession().createQuery(
                 "SELECT a FROM LineaPedido a WHERE a.pedido.idPedido = :idPedido")
                 .setParameter("idPedido", idPedido); 

      // Query query = getSession().createQuery("SELECT u FROM LineaPedido u");
        List<LineaPedido> listaPedido= query.list();
        return listaPedido;
      
      
      
      
      
      }
}
