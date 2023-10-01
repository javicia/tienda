/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.pedidoservice;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.recomendacion.Recomendacion;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;

/**
 *
 * @author Emilio
 */
public interface PedidoService {
  
  public void registrarPedido (Pedido pedido) ;
  public List<Pedido> listaPedidos();
  public Pedido buscarPedido (long idPedido) throws InstanceNotFoundException;
  public List<LineaPedido> listaPedidosLineas(long idPedido);
  public void actualizarEstado (long idPedido, String estado) throws InstanceNotFoundException;
  
   public void actualizarNumVeces (long id1, long id2) throws InstanceNotFoundException;
      public long findIdRopa1 (long idRopa);
      
     public long findIdRopa2 (long idRopa);
  public void insertarPedidoService (long id1, long id2);
  
   public List<Recomendacion> listaRecomendaciones () ;
   public List<Long> ids1 (long idRopa2);
           public List<Long> ids2 (long idRopa1); 
}
