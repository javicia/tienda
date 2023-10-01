/********************* EMILIO DOMÍNGUEZ SACHEZ *********************/

package es.javi.tiendaonline.model.pedido;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;


public interface PedidoDao extends GenericDao<Pedido, Long>{
    
    
    
    /**
     * Returns an Pedido by idPedido ( Ropa identifier)
     *
     * @param idPedido the pedido identifier
     * @return the Pedido
     */
  
      public Pedido findByidPedido(long idPedido) throws InstanceNotFoundException;

      public void anadirPedido (Pedido pedido);
    
      public List<Pedido> listaTodosPedidos ();
      
      public List<Pedido> listaPedidosUsuario (long userProfileId);
      public List<LineaPedido> listaPedidosLineas (long idPedido);
}
