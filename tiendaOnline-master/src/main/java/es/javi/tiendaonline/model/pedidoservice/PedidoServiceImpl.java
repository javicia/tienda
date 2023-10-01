/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.pedidoservice;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.pedido.PedidoDao;
import es.javi.tiendaonline.model.recomendacion.Recomendacion;
import es.javi.tiendaonline.model.recomendacion.RecomendacionDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Emilio
 */
@Service("PedidoService")
@Transactional
public class PedidoServiceImpl implements PedidoService {
  
  
  @Autowired
    private PedidoDao pedidoDao;
   @Autowired
    private RecomendacionDao recomendacionDao;
  
  public void registrarPedido (Pedido pedido) {
    
    pedidoDao.anadirPedido(pedido);
  
  }
  
  public void aunmentarnumVeces (Recomendacion recomendacion) {
  recomendacion.setNumVeces(recomendacion.getNumVeces()+1);
  }
  
   @Transactional
   public List<Pedido> listaPedidos() {
   
     return pedidoDao.listaTodosPedidos();
   
   }
   
   @Transactional
   public List<LineaPedido> listaPedidosLineas(long idPedido) {
     return pedidoDao.listaPedidosLineas(idPedido);
   }
   
   
     @Transactional(readOnly = true)
     public Pedido buscarPedido (long idPedido) throws InstanceNotFoundException{
     
     return pedidoDao.find(idPedido);
     }
     
     
     
     
    public void actualizarEstado (long idPedido, String estado) throws InstanceNotFoundException {
      Pedido ped = pedidoDao.find(idPedido);
      ped.setEstado(estado);
    }
    
    
    
    
    public List<Recomendacion> listaRecomendaciones ()  {
    
         return recomendacionDao.listaTodasRecomendaciones();
    
    }
    
    public long findIdRopa1 (long idRopa) {

      List<Recomendacion> lista = listaRecomendaciones();
      for (int i=0; i<lista.size();i++) {
        if (lista.get(i).getIdRopa1() == idRopa){
          return lista.get(i).getIdRopa1();
        }
        
     
    }
    return 0;  
    }
     
     
    public long findIdRopa2 (long idRopa) {
      
      List<Recomendacion> lista = listaRecomendaciones();
      for (int i=0; i<lista.size();i++) {   
        if (lista.get(i).getIdRopa2() == idRopa){
          return lista.get(i).getIdRopa2();
        }     
    }
    return 0;  
    }  
    
    public List<Long> ids1 (long idRopa2) {
     return recomendacionDao.listadeIds1(idRopa2);
    }
    
    public List<Long> ids2 (long idRopa1) {
      return recomendacionDao.listadeIds2(idRopa1);
    }
    
    public void actualizarNumVeces (long id1, long id2) throws InstanceNotFoundException {
      
      Recomendacion re = recomendacionDao.findByIdsRopa(id1, id2);
      re.setNumVeces(re.getNumVeces()+1);
    }
    
    
    public void insertarPedidoService (long id1, long id2) {
    Recomendacion re = new Recomendacion (id1,id2,1);
      recomendacionDao.insertarRecomendacion(re);
    }
    


    
}
