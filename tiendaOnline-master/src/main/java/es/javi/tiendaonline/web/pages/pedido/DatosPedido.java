/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.pedido;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.javi.tiendaonline.model.lineapedidoservice.LineaPedidoService;
import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.pedidoservice.PedidoService;
import es.javi.tiendaonline.model.userprofile.UserProfile;
import es.javi.tiendaonline.model.userservice.UserService;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;
import es.javi.tiendaonline.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class DatosPedido {
  
     String firstName;
     String lastName;  
     String email;    
     String dni;    
     long telefono;
     int numeroPuntos;
     int precioTotal;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;
    private Pedido pedido;
    private long idPedido;
    
     @Property 
  LineaPedido lineaPedido;
  @Inject
  private PedidoService pedidoService;
@Inject
private LineaPedidoService lineaPedidoService;
    

  public void setIdPedido(long idPedido) {
    this.idPedido = idPedido;
  }
    

	void onActivate(Long idPedido) {
          
          this.idPedido = idPedido;
          System.out.println("PROBANDO "+idPedido);
        } 
        
        Long onPassivate() {
		return idPedido;
	}
  
   public List<LineaPedido> getListaProductosPedido () {
     
    return lineaPedidoService.listarLineasPorIdPedido(this.idPedido);
    
  }

  public long getPrecioTotal() {
    long aux=0;
    List<LineaPedido> list = lineaPedidoService.listarLineasPorIdPedido(this.idPedido);
    for(int i=0;i<list.size();i++) {
      aux= aux + (list.get(i).getPrecioUnitario() * list.get(i).getNumeroUnidades() );    
    }
    return aux;
  }
    
  
  public String getFirstName() throws InstanceNotFoundException {
    System.out.println(this.idPedido + " DATA PEDDIODDODODODO");
              System.out.println("PROBANDO "+idPedido);

     UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    return userProfile.getFirstName();
  }

  public void setFirstName(String firstName) throws InstanceNotFoundException {
         UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    this.firstName = userProfile.getFirstName();
  }

  public String getLastName() throws InstanceNotFoundException {
    UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    return userProfile.getLastName();
  }

  public void setLastName(String lastName) throws InstanceNotFoundException {
 UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    this.firstName = userProfile.getLastName();  }

  public String getEmail() throws InstanceNotFoundException {
     UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
     return userProfile.getEmail();
  }

  public void setEmail(String email) throws InstanceNotFoundException {
    UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    this.email = userProfile.getEmail();
  }

  public String getDni() throws InstanceNotFoundException {
     UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    return userProfile.getDni();
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public long getTelefono() throws InstanceNotFoundException {
 UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
   return userProfile.getTelefono();  }

  public void setTelefono(long telefono) {
    this.telefono = telefono;
  }

  public int getNumeroPuntos() throws InstanceNotFoundException {
    UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    return userProfile.getNumeroPuntos();  
  }

  public void setNumeroPuntos(int numeroPuntos) {
    this.numeroPuntos = numeroPuntos;
  }
    
    
    
    
    
    
  
}
