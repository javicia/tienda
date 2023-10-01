/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.lineapedido;

import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;

/**
 *
 * @author emilio
 */
public interface LineaPedidoDao {
     /**
     * Returns an LineaPedido by idLineaPedido ( not identifier)
     *
     * @param idLineaPedido
     * @return LineaPedido
     */
          public LineaPedido findByIdLineaPedido(int idLineaPedido) throws InstanceNotFoundException;
          public void anadirLineaPedido (LineaPedido lineaPedido) ;
          
          public List<LineaPedido> ListarLineasPedido (long idPedido);
}
