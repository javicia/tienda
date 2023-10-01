/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.stocktalla;

import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class CrearStock {
  
 
    
    @Property
    Ropa ropa;
    
  
    
     @Inject
    private RopaService ropaService;
     
    
    
    public List<Ropa> getListaRopa() {
    return ropaService.listaRopa();
  }


            
    
    
    public List <Ropa> getRopas() {
    
    return ropaService.listaRopa();
    
    
    }
    

}
