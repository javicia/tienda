/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.etiquetaservice;

import es.javi.tiendaonline.model.etiqueta.Etiqueta;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;

/**
 *
 * @author Emilio
 */

public interface EtiquetaService {
   
  
      public Etiqueta registrarEtiqueta (String nombreEtiqueta);  
      
      
        public Etiqueta findEtiqueta(Long idEtiqueta)
            throws InstanceNotFoundException;
        /*
        public void actualizarRopa (String nombre, int precio,
            String color, String marca, String descripcion) 
                throws InstanceNotFoundException;
        
        
        */
        public Etiqueta findEtiqueta(String nombreEtiqueta) throws InstanceNotFoundException;

        
        public List<Etiqueta> listaEtiqueta();
      
      
  
}
