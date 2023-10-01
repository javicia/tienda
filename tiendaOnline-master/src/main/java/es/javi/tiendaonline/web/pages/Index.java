package es.javi.tiendaonline.web.pages;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.adjuntoservice.AdjuntoService;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.model.util.ListadoIndex;
import es.javi.tiendaonline.web.pages.ropa.MiImagen;
import es.javi.tiendaonline.web.pages.ropa.VerRopa;
import es.javi.tiendaonline.web.util.Carrito;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;

public class Index {
     
    
    long id;
    @Property
    private Ropa ropa;
    
    @Property
    private Adjunto adjunto;
        
    @Property
    String nombreAdjunto;

    String direccion ;
    
    private int precioCarrito=0;
    
       @Property
    String id6 = "ropa/miimagen/5";
    
    
     @Inject
    private RopaService ropaService;
     
     @Inject
     private AdjuntoService adjuntoService;
            
     
     @Property
     @SessionState(create=false)
     private Carrito carrito = new Carrito ();
     
     
     private boolean siHayCarrito=false;
     
     @Inject
private AssetSource assetSource;


     
     public Asset getSignImage() {
    final String path = "Users/Emilio/Dropbox/Facultad/PFC/pojo-app/ ";
    return assetSource.getContextAsset(path, null);
}
    
    public List<Ropa> getListaRopa() {
    return ropaService.listaRopa();
  }

    
  
    public List<Adjunto> getListaAdjunto() {
      return adjuntoService.listaAdjuntos();
    }
   
    public List <Ropa> getRopas() {
    
      return ropaService.listaRopa();
    
    }
    
    public void getSiHayCarrito () {
    
      if (carrito.getProductos().isEmpty()) {
       siHayCarrito= false;
      }else {
        siHayCarrito = true;
      }
    
    
    }

  public boolean isSiHayCarrito() {
   if (carrito.getProductos().isEmpty()) {
     return false;
    }else {
      return true;
    }
  }

  public void setSiHayCarrito(boolean siHayCarrito) {
    this.siHayCarrito = siHayCarrito;
  }

  
  
  public String getDireccion() {
    return   System.getProperty("user.dir");
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
    
    
    

  public long getPrecioCarrito() {
    return carrito.calculaPrecio();
  }

  public void setPrecioCarrito(int precioCarrito) {
    this.precioCarrito = precioCarrito;
  }
  
  
  
  
  
  public List getRopaIndex () {
  
  return adjuntoService.listaRopaIndex();
  }
  

  
    public List<Blob> getRopaIndexSoloImagen () {
  
  return adjuntoService.listaRopaIndexSoloImagen();
  }
  
  
  
  
  public Blob getBlob1() throws InstanceNotFoundException {
        Blob a = ropaService.recuperarImagen(id);
        
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
    
    

   public List<Link> getMostrarLista () {
   List prueba = getRopaIndex();
   List<Link> list2 = new ArrayList<Link>();
   for (int i=0;i< prueba.size();i++) {
     //Adjunto id =prueba.get(i).getRopa().getIdRopa();
       Link link = getImageLink();
       list2.add(link);   
   }   
   return list2;
   }

    @Property
    private ListadoIndex listado;
    

    
    
    public String getNombre2(long idRopa) throws InstanceNotFoundException {
    return ropaService.findRopa(idRopa).getNombre();
    }
    
    public String getDescripcion(long idRopa) throws InstanceNotFoundException{
    
        return ropaService.findRopa(idRopa).getDescripcion();
    }
    
    
    
    public List<ListadoIndex> getListados() throws InstanceNotFoundException {
        List<Adjunto> listaAdj = this.getListaAdjunto();
        List<ListadoIndex> auxiliar = new ArrayList<ListadoIndex>();
        for (Adjunto listaAdj1 : listaAdj) {
            long id2 = listaAdj1.getRopa().getIdRopa();
            String nuevo = ("ropa/miimagen/"+id2);
            ListadoIndex listado2 = new ListadoIndex (id2,nuevo, this.getNombre2(id2),this.getDescripcion(id2));
            auxiliar.add(listado2);
        }
        return auxiliar;
    }
    
    
    
    
    
}


     


