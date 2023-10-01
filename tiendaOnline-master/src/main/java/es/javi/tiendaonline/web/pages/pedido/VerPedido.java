
package es.javi.tiendaonline.web.pages.pedido;
import es.javi.tiendaonline.model.lineapedidoservice.LineaPedidoService;
import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.pedidoservice.PedidoService;
import es.javi.tiendaonline.model.userprofile.UserProfile;
import es.javi.tiendaonline.model.userservice.UserService;
import es.javi.tiendaonline.web.pages.Index;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;
import es.javi.tiendaonline.web.util.Carrito;
import es.javi.tiendaonline.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.Date;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class VerPedido {
  
  
  @Property
  @SessionState(create=false)
  private Carrito carrito;
  
  
  UserProfile userProfile;

  boolean verMensaje = false;
  boolean Hay = true;

  @Component
  Form borrarCarritoForm;
  
  @Component
  Form tramitarPedidoForm;

  @Inject
  private LineaPedidoService lineaPedidoService;
  
  @Inject
  private PedidoService pedidoService;
      
        
  @SessionState(create=false)
  private UserSession userSession;
    
  @Inject
  private UserService userService;
      
  @InjectPage
private DatosPedido datosPedido;

  
  @Property
    @Persist(PersistenceConstants.FLASH)
    // We use a String, not a Boolean, in the radio group value so that we can represent null. Boolean can't represent
    // null because Tapestry will coerce it to Boolean.FALSE. See https://issues.apache.org/jira/browse/TAPESTRY-1928 .
    private String valueForMyBoolean;
 
    @Property
    private Boolean myBoolean;

 @Inject
 Block bloque1;
@Inject
 Block bloque2;
    @Inject
 Block bloque3;
 
   @Persist
    private int whichCase;
 
    @Inject
    private Block case1, case2;
 


    public Block getBloque1() {
        return bloque1;
    }

    public void setBloque1(Block bloque1) {
        this.bloque1 = bloque1;
    }




  public boolean isHay() {
    
     if (!carrito.getProductos().isEmpty()){
      return true;}
     else{ return false;}
  }

  public void setHay(boolean hay) {
    
    if (!carrito.getProductos().isEmpty()){
      Hay = true;}
    else {Hay = false;}
  
  }
  
  public boolean isVerMensaje() {
    
    return verMensaje;
  }

  public void setVerMensaje(boolean verMensaje) {
    this.verMensaje = verMensaje;
  }

  
  
  public List getProductos() {
    return carrito.getProductos();
  }

//CADA LINEA DEL CARRITO es una linea De pedido en la persistencia
  //Despues todas esas lineas se meten en un PEDIDO (una vez tramitado....a)

  void onValidateSucessborrarCarritoForm() {
    if (borrarCarritoForm.isValid()) {}
  }
  
  
  void onValidatetramitarPedidoForm() {}
  
  
   Object onSuccessFromborrarCarritoForm() {
     
      //BORRA EL CARRITO!!
      carrito.vaciarCarrito();
   return Index.class;
   
   }
  
   
   Object onSuccessFromtramitarPedidoForm() throws InstanceNotFoundException {

   //  setVerMensaje(true);
   //  if (tramitarPedidoForm.isValid())
     //carrito = null;         
     userProfile = userService.findUserProfile(userSession.getUserProfileId());
     Date ahora = new Date();
     
     //falta por registrar el pedido
     Pedido pedido = new Pedido (ahora,carrito.calculaPrecio(),userProfile,(String)"pendiente");     
      pedidoService.registrarPedido(pedido);    
      lineaPedidoService.registrarLineaPedido(carrito.getProductos(),pedido);
      carrito.vaciarCarrito();
     //return VerPedidosTramitado.class;
        datosPedido.setIdPedido(pedido.getIdPedido());
         return datosPedido;

      }
   
	   void setupRender() {

        // First time in, valueForMyBoolean will be null.

        if (valueForMyBoolean == null) {
            valueForMyBoolean = "T";
        }

        // Set myBoolean based on valueForMyBoolean.

        if (valueForMyBoolean.equals("T")) {
            myBoolean = Boolean.TRUE;
        }
        else if (valueForMyBoolean.equals("F")) {
            myBoolean = Boolean.FALSE;
        }
        else if (valueForMyBoolean.equals("N")) {
            myBoolean = null;
        }
        else {
            throw new IllegalStateException(valueForMyBoolean);
        }
    }
           
    
    public Object getCase()
    {
        switch (whichCase)
        {
            case 1:
                return case1;
            case 2:
                return case2;
            default:
                return null;
        }
    }
           
   /*   public Block getCase() {
        if (myBoolean == null) {
            return bloque1;
        }
        else if (myBoolean == Boolean.TRUE) {
            return bloque2;
        }
        else {
            return bloque3;
        }
    }
      
      */
  
   

}
