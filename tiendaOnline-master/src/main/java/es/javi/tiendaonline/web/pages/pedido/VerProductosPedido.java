/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.pedido;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.javi.tiendaonline.model.lineapedidoservice.LineaPedidoService;
import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.pedidoservice.PedidoService;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.javi.tiendaonline.model.stocktallaservice.StockTallaService;
import es.javi.tiendaonline.web.pages.Index;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
public class VerProductosPedido {
  
  long idpe;
  @Property
  Pedido pedido;
  Pedido pedido2;
  
  @Property 
  LineaPedido lineaPedido;

  
  @Inject
  private PedidoService pedidoService;
  
  
@Inject
private LineaPedidoService lineaPedidoService;

@Inject
private StockTallaService stockTallaService;

@Inject
private RopaService ropaService;



@Component
private Form comprobarStockForm;
  
  public long getAccountContext() {

    return pedido.getIdPedido();
  }


  public long getIdpe() {
    return idpe;
  }

  public void setIdpe(long idpe) {
    this.idpe = idpe;
  }

    
  void onActivate (long pedidoId) throws InstanceNotFoundException {
    this.idpe = pedidoId;
    this.pedido = pedidoService.buscarPedido(pedidoId);

  }
  
  long onPassivate () {
    
    return pedido.getIdPedido();
    }
   
   
  public List<LineaPedido> getListaProductosPedido () {  
    return lineaPedidoService.listarLineasPorIdPedido(pedido.getIdPedido());
    
  }
  
  //Hacer metodo que metiendo un id1 devuelva una lista de id2??
  void anadirComprarRecomendaciones (ArrayList<Long> listaIds) throws InstanceNotFoundException {
    if (listaIds.size() > 1) {
    boolean realizado = false;
    for (int i=0; i<listaIds.size(); i++) {
      long id =pedidoService.findIdRopa1(listaIds.get(i));
      long id2 = pedidoService.findIdRopa2(listaIds.get(i));
       long id3=0;
      long id4=0;       
      if (id!=0) {  
        List<Long> ids2 = pedidoService.ids2(listaIds.get(i));
        //lo encuentra en idRopa1    
        for (int j=i; j<listaIds.size();j++) {
        // recorrer la lista buscando solo en idRopa2
        if (ids2.contains(listaIds.get(j)) ){
          pedidoService.actualizarNumVeces(listaIds.get(i), listaIds.get(j));
        } else {
          if (listaIds.get(i)!=listaIds.get(j) )
         pedidoService.insertarPedidoService(listaIds.get(i),listaIds.get(j));
        }
               
      }
     }else if (id2!=0) {
        //lo encuentra en idRopa2
          List<Long> ids1 = pedidoService.ids1(id2);
        for (int z=i;z<listaIds.size();z++){
        //Recorrer la lista buscando solo en idRopa1        
            if (ids1.contains(listaIds.get(z)) ){
            pedidoService.actualizarNumVeces(listaIds.get(z),id2);          
        } else {
              if (listaIds.get(z)!=listaIds.get(i))
            pedidoService.insertarPedidoService(listaIds.get(z),listaIds.get(i));
            }  
      }
     }
     else if (id==0 & id2==0) {
      //no está en idropa1 ni en idropa2
       //por lo que hai q añadir ese id, con el resto a recomendaciones....
        //añadir listaIds.get(i) en id1 con 
        //HACER YA, PARA INSERTAR RECOMENDACION Y PARA ACUALIZAR NUMVECES
       // Ropa ropa1 = ropaService.findRopa(id);
         for (int k=0; k<listaIds.size(); k++) {
          if (listaIds.get(i) != listaIds.get(k)) {          
             pedidoService.insertarPedidoService(listaIds.get(i), listaIds.get(k));   
           }
         }
       //  i=listaIds.size();
      }
    }
   
    
    }
  } 
  
  
  
  void onValidateFromcomprobarStockForm () throws InstanceNotFoundException{

    //Esto hai que Hacerlo con EL DAO es decir un metodo UPDATE!!
    
    List<LineaPedido> lineas = pedido.getLineaPedidos();
    ArrayList<Long> listaIds = new ArrayList<Long> ();
    Iterator i = lineas.iterator();
    LineaPedido lineaPedido = null;
    int count = 0;
    while (i.hasNext()) {
       count++;
       LineaPedido lineaActual = (LineaPedido)i.next();
       listaIds.add(lineaActual.getIdRopa());
       StockTalla stock = stockTallaService.findStockTalla(lineaActual.getIdStockTalla());
       if (stock.getStock() < lineaActual.getNumeroUnidades()){        
          Ropa ropa = ropaService.findRopa(lineaActual.getIdRopa());
          pedidoService.actualizarEstado(pedido.getIdPedido(),ropa.getNombre() + " Producto Sin STOCK");         
          
       } 
       else {
         stockTallaService.actualizarStock
             (stock.getIdStockTalla(), stock.getStock() - lineaActual.getNumeroUnidades());
         pedidoService.actualizarEstado(pedido.getIdPedido(), "Enviado");      
       }

        
      }
    anadirComprarRecomendaciones(listaIds);

    }

  Object onSuccessFromcomprobarStockForm () {
  
   return VerPedidosTramitado.class;
  
  }
}
