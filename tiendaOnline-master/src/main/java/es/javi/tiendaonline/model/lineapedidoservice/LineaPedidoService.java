/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.lineapedidoservice;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.javi.tiendaonline.model.pedido.Pedido;

import java.util.List;

/**
 *
 * @author Emilio
 */
public interface LineaPedidoService {
  
    public void registrarLineaPedido (List Productos, Pedido pedido) ;

    public List<LineaPedido> listarLineasPorIdPedido (long idPedido);
  
}
