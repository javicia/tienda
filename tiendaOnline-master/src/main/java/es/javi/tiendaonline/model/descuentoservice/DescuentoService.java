/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.descuentoservice;

import es.javi.tiendaonline.model.descuento.Descuento;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;

/**
 *
 * @author Emilio
 */
public interface DescuentoService {
  
  
    public Descuento registrarDescuento (String nombreDescuento, Long porcentajeDescuento);  
      
         public Descuento registrarDescuento ( Long porcentajeDescuento) ;

        public Descuento findDescuento(Long idDescuento)
            throws InstanceNotFoundException;
        
        /*
        public void actualizarRopa (String nombre, int precio,
            String color, String marca, String descripcion) 
                throws InstanceNotFoundException;
        
        
        
        public Etiqueta findEtiqueta(String nombreEtiqueta) throws InstanceNotFoundException;

        */
        public List<Descuento> listaDescuento();
}
