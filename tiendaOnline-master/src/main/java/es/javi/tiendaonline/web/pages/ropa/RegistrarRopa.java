package es.javi.tiendaonline.web.pages.ropa;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.adjuntoservice.AdjuntoService;
import es.javi.tiendaonline.model.categoria.Categoria;
import es.javi.tiendaonline.model.categoriaservice.CategoriaService;
import es.javi.tiendaonline.model.descuento.Descuento;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropaservice.RopaService;
import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.javi.tiendaonline.model.stocktallaservice.StockTallaService;
import es.javi.tiendaonline.web.pages.Index;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import org.apache.tapestry5.SelectModel;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.upload.services.UploadedFile;

/**
 *
 * @author emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class RegistrarRopa {
  
    @Property
    private String nombre;
    
    @Property
    private int precio;
    
    @Property
    private String color;
    
    @Property
    private String marca;
    
    @Property
    private String descripcion;
    
    @Property
    private String nombreAdjunto;
    

    
    @Property
    private long idCategoria;
    
     @Property
    private UploadedFile file;
    
    @Property 
    private int stock;
    
    @Property
    private String talla;
        
   /* @Inject
    @Path ("context:/home/emilio/Dropbox/Facultad/PFC/pojo-app/img/Pantalon_Depor1.png")
    private Asset imagen1;  
    */
    @Inject
    private RopaService ropaService;

    @Inject
    private StockTallaService stockTallaService;
    
    @Component
    private Form registrarRopaForm;
    

    @Property
    Categoria categoria;

   @Inject
    private AdjuntoService adjuntoService;
    @Inject
    private CategoriaService categoriaService;
    
    @Property
    private SelectModel categoriaSelectModel;

    @Inject
    SelectModelFactory selectModelFactory;
    
    
    public List<Categoria> getListaCategoriaRopa() {
      
    return categoriaService.listaCategoria();
    
  }
       
    
    void onValidateFromregistrarRopaForm() throws InstanceNotFoundException, SQLException {
            
      if (!registrarRopaForm.isValid()) 
          {
           return;
          }
    //  File copied = new File("/Users/Emilio/Dropbox/Facultad/PFC/pojo-app/img/" + file.getFileName());
//
      //file.write(copied);
      

        Ropa ropa = ropaService.registrarRopa(nombre, precio, color, marca, 
                  descripcion, idCategoria);   
     
        File file2 = new File("/home/emilio/Dropbox/Facultad/PFC/pojo-app/img/" +  file.getFileName());
        byte[] imageData = new byte[(int) file2.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file2);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Blob blob1 = new javax.sql.rowset.serial.SerialBlob(imageData);
        blob1.setBytes(1, imageData);
        Adjunto adjunto = ropaService.registrarAdjunto(file.getFileName(), blob1, ropa);

      
      StockTalla stockTalla = stockTallaService.registrarStockTalla(talla, stock, ropa);
      
      
               
        byte[] bAdjunto = ropaService.verImagen(ropa.getIdRopa());
 

   
          
        }

        
     Object onSuccessFromregistrarRopaForm() {
          
          return Index.class;
      }
        



     // Muestra el SELECT de la lista de categorias, para seleccionar una.
    void setupRender() {
      
        List<Categoria> categorias = categoriaService.listaCategoria();
        categoriaSelectModel = selectModelFactory.create(categorias, "nombreCategoria");
    }
    
    
}
