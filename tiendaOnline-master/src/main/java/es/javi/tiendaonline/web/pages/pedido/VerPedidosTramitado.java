/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.pedido;

import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.pedidoservice.PedidoService;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
public class VerPedidosTramitado {
  
  @Property
  Pedido pedido;
  
  
  @Inject
  private PedidoService pedidoService;
 
  
  public List<Pedido> getPedidosTramitados () {
    return pedidoService.listaPedidos();
  
  }

  
 
  
  
  
}
