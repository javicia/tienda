/********************* EMILIO DOMÍNGUEZ SACHEZ *********************/
 package es.javi.tiendaonline.model.pedido;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
 @Repository("PedidoDao")
public class PedidoDaoHibernate extends
		GenericDaoHibernate<Pedido, Long> implements PedidoDao {
     @Autowired
      private SessionFactory sessionFactory;
     
    public Pedido findByidPedido(long idPedido) throws InstanceNotFoundException{
    
 
    	Pedido pedido = (Pedido) getSession().createQuery(
    			"SELECT u FROM Pedido u WHERE u.idPedido = :idPedido")
    			.setParameter("idPedido", idPedido)
    			.uniqueResult();
    	if (pedido == null) {
   			throw new InstanceNotFoundException(idPedido, Pedido.class.getName());
    	} else {
    		return pedido;
    	} 
    }
    
    
    
    public void anadirPedido (Pedido pedido) {
    
      sessionFactory.getCurrentSession().save(pedido);
      
    
    }
    
    public List<Pedido> listaTodosPedidos () {
      
    Query query = getSession().createQuery("SELECT u FROM Pedido u");
        List<Pedido> listaTotosPedidos= query.list();
        return listaTotosPedidos;
    
    
    
    }
    
       /* CREO QUE TODO ESTO FALLA PORQUE HAI Q FACER UN IN
          TIPO ALGO DE ESTO 
       *  SELECT u FROM User u, IN (u.addresses) AS a WHERE a.city = 'Sydney'
       * SELECT * FROM CAT C INNER JOIN MATE M ON C.MATE_ID = M.ID WHERE M.ID = 13
       * 
       * select count(*) from BillDetails bd join bd.billProductSet ps 
           where ps.product.id=1002
           and bd.client.id=1"
       * 
       * */
    
     public List<LineaPedido> listaPedidosLineas (long idPedido) {
      
    Query query = getSession().createQuery("SELECT u FROM Pedido u, IN (u.lineaPedidos) AS a WHERE a.pedido.idPedido = :idPedido ").setParameter("idPedido", idPedido);
        List<LineaPedido> listaTotosPedidos= query.list();
        return listaTotosPedidos;
    
    
    
    }
    
       public List<Pedido> listaPedidosUsuario(long userProfileId) {
    
        Query query = getSession().createQuery(
                "SELECT a FROM Pedido a WHERE a.usuario.userProfileId = :userProfileId").setParameter("userProfileId",userProfileId);
        List<Pedido> listaPedido= query.list();
        return listaPedido;

     }
    
    
    
    
    
    
}
