/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.ropa;

import es.javi.tiendaonline.model.comentario.Comentario;
import es.javi.tiendaonline.model.comentarioservice.ComentarioService;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.javi.tiendaonline.model.stocktallaservice.StockTallaService;
import es.javi.tiendaonline.model.userprofile.UserProfile;
import es.javi.tiendaonline.model.userservice.UserService;
import es.javi.tiendaonline.web.pages.Index;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;
import es.javi.tiendaonline.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;


/**
 *
 * @author Emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class VerRopaComentario {
  
  
    long idrop2;
    Ropa ropa;
    Ropa ropa2;
    UserProfile userProfile;
    StockTalla stockTalla;
    
    @Property
    Comentario comentario;

    @Property
    private String coment;
    
    @Property
    private int stock;
    
    @Property
    private String talla;
    
    
    @Component
    private Form comentarioForm;
    @Component
    private Form anadirStockForm;
    @Property
    List<Comentario> lista2;
 
    
     @Inject
    private RopaService ropaService;
  
    @Inject
    private StockTallaService stockTallaService2;
    
    @SessionState(create=false)
    private UserSession userSession;
    @Inject
    private UserService userService;
    
    @Inject
    private ComentarioService comentarioService;

    
    
  public long getIdrop2() {
    return idrop2;
  }

  public void setIdrop2(long idrop2) {
    this.idrop2 = idrop2;
  }

  public StockTalla getStockTalla() {
    return stockTalla;
  }

  public void setStockTalla(StockTalla stockTalla) {
    this.stockTalla = stockTalla;
  }

  
  public Ropa getRopa() {
    return ropa;
  }

  public void setRopa(Ropa ropa) {
    this.ropa = ropa;
  }

  public UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  
  public long getAccountContext() {

    return ropa.getIdRopa(); 
 }

   

  void onActivate(long ropaId) throws InstanceNotFoundException  
  {
    this.idrop2= ropaId;
    ropa = ropaService.findRopa(ropaId);
    
  }
    
    long onPassivate () {
    
    return ropa.getIdRopa();
    }
   
    void onValidateFromcomentarioForm() throws InstanceNotFoundException  
     {
       
      if (!comentarioForm.isValid()) 
        {
         return;
        }
      userProfile = userService.findUserProfile(userSession.getUserProfileId());
      comentarioService.registrarComentario(coment, userProfile, ropa);
     
     }
    void onValidateFromanadirStockForm () throws InstanceNotFoundException
    { 
      if (!anadirStockForm.isValid())
            return;
      long id = ropa.getIdRopa();
      Ropa ropa2 = ropaService.findRopa(id);
      StockTalla stockTalla2 = stockTallaService2.registrarStockTalla(talla, stock, ropa2);
    
    }
    
      Object onSuccess() {
        
       return Index.class;
      }
      
    
      public List<StockTalla> getListaStockTalla() {
        
        long idrop = ropa.getIdRopa();
        return stockTallaService2.listaStockTalla(idrop);
      } 

      
      
  public List<Comentario> getListaComentarios() throws InstanceNotFoundException {

    long idrop = ropa.getIdRopa();
    return comentarioService.listaComentario(idrop);
    
  }


            
    
  
}
