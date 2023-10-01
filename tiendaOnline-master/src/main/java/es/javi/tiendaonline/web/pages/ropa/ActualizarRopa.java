    /*
 */

package es.javi.tiendaonline.web.pages.ropa;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropa.RopaDao;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author emilio
 */

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ActualizarRopa {
    

   /* @Property
    private Adjunto adjunto;*/  
    
    
    @Property
    Ropa ropa;
    
    @Property
    StockTalla stockTalla;
    
  
    
     @Inject
    private RopaService ropaService;
        
    
    public List<Ropa> getListaRopa() {
    return ropaService.listaRopa();
  }   
    
    public List <Ropa> getRopas() {
    
    return ropaService.listaRopa();
    
    
    }    
     
    
    
}
