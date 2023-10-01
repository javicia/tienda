/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.ropa;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.comentario.Comentario;
import es.javi.tiendaonline.model.comentarioservice.ComentarioService;
import es.javi.tiendaonline.model.pedidoservice.PedidoService;
import es.javi.tiendaonline.model.recomendacion.Recomendacion;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.javi.tiendaonline.model.stocktallaservice.StockTallaService;
import es.javi.tiendaonline.model.userprofile.UserProfile;
import es.javi.tiendaonline.model.userservice.UserService;
import es.javi.tiendaonline.web.pages.Index;
import es.javi.tiendaonline.web.util.Carrito;
import es.javi.tiendaonline.web.util.LineaCarrito;
import es.javi.tiendaonline.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.SelectModelFactory;


/**
 *
 * @author Emilio
 */
public class VerRopa {
   @Property
    private Adjunto adjunto;
    
    long id;
    Ropa ropa;
    UserProfile userProfile;
    
    @Property
    Comentario comentario;
    @Property
    Recomendacion recomendacion;
    @Property
    StockTalla stockTalla;
    
    @Property
    private long idStockTalla;

    @Inject
    private RopaService ropaService;
    
    @Inject
    private StockTallaService stockTallaService;
    
    @Property
    private SelectModel stockTallaSelectModel;
    
    @Inject
    SelectModelFactory selectModelFactory;  
    
    @Inject
    private ComentarioService comentarioService;
    
    @Component
    Form anadirCarritoForm;
    
     @SessionState(create=false)
    private UserSession userSession;
    @Inject
    private UserService userService;
    
    
    @Inject
    private PedidoService pedidoService;
    
    @Property
    private String coment;
    
     @Component
    private Form comentarioForm;
     
    @SessionState(create=false)
    private Carrito carrito= new Carrito();

    private InputStream blob1;
      // private Blob blob2;
    
  public Ropa getRopa() {
    return ropa;
  }

  public void setRopa(Ropa ropa) {
    this.ropa = ropa;
  }
    
    
    public long getAccountContext() {
      
      return ropa.getIdRopa(); 
      
    }

    
    public List<Ropa> getListaRopa() {
      
      return ropaService.listaRopa();
    
    }
    
    public List <Ropa> getRopas() {
    
      return ropaService.listaRopa();
    
    
    }

    void onActivate(long ropaId) throws InstanceNotFoundException  
    {
        ropa  = ropaService.findRopa(ropaId);
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
    

  void onValidateFromanadirCarritoForm () throws InstanceNotFoundException  {
   
    if (!anadirCarritoForm.isValid()) {

      return;

    }
    //NON ME DEIXA FACER ESTO, MIRAR OUTRAS ALTERNATIVAS!! 
    LineaCarrito lineaCarrito2 = new LineaCarrito
            (ropa.getPrecio(),ropa, idStockTalla);
    carrito.anadirProducto(lineaCarrito2); 
      
  }
     Object onSuccess () {
     
       return Index.class;
     
     }
     
    void setupRender () {
    
      List<StockTalla> stockTallas = stockTallaService.listaStockTalla(ropa.getIdRopa());
      stockTallaSelectModel = selectModelFactory.create(stockTallas, "talla");    
    }
 
  public List<Comentario> getListaComentarios() throws InstanceNotFoundException {

    long idrop = ropa.getIdRopa();
    return comentarioService.listaComentario(idrop);
    
  }
  
    public List<Recomendacion> getListaRecomendaciones() throws InstanceNotFoundException {

    return pedidoService.listaRecomendaciones();
    
  }

    public Blob getBlob1() throws InstanceNotFoundException {
        Blob a = ropaService.recuperarImagen(ropa.getIdRopa());
        
        return a;
    }

     
     
       public StreamResponse onReturnStreamResponse() {
        return new StreamResponse() {
            InputStream inputStream;
            @Override
            public void prepareResponse(Response response) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                try {
                    inputStream = getBlob1().getBinaryStream();
                } catch (InstanceNotFoundException ex) {
                    Logger.getLogger("FALLA inputStream1"+VerRopa.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger("FALLA inputStream1"+VerRopa.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    response.setHeader("Content-Length", "" + inputStream.available());
                }
                catch (IOException e) {
                    // Ignore the exception in this simple example.
                }
            }

            @Override
            public String getContentType() {
                return "png/Image";
            }

            @Override
            public InputStream getStream() throws IOException {
                return inputStream;
            }
        };
    }
    
       
    @Inject
    private PageRenderLinkSource pageLink;

    public Link getImageLink()
    {
        return pageLink.createPageRenderLinkWithContext(MiImagen.class,ropa.getIdRopa());
    }

}
     
         

     
     

            
        