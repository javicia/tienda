/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.descuento;

import es.javi.tiendaonline.model.descuento.Descuento;
import es.javi.tiendaonline.model.descuentoservice.DescuentoService;
import es.javi.tiendaonline.web.pages.Index;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
public class CrearDescuento {
  
  
       @Component
       private Form crearDescuentoForm;
       
       @Property
       private long porcentajeDescuento;
       
       
       @Property
       private String nombreDescuento;
       
       @Inject 
       DescuentoService descuentoService;
             
       
       
        void onValidateFromcrearDescuentoForm()  throws InstanceNotFoundException
        {
            if (!crearDescuentoForm.isValid()) 
          {
              return;
          }
          Descuento descuento = descuentoService.registrarDescuento(nombreDescuento, porcentajeDescuento);
    
        }
            
        Object onSuccess() {
          return Index.class;
      }
 
}
