

package es.javi.tiendaonline.model.ropaservice;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.adjunto.AdjuntoDao;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.ropa.RopaDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.sql.Blob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emilio
 */
@Service("RopaService")
@Transactional
public class RopaServiceImpl implements RopaService {

  @Autowired
  private RopaDao ropaDao;      
  @Autowired
  private AdjuntoDao adjuntoDao;
    
       
  
  public Ropa registrarRopa (String nombre, int precio,
            String color, String marca, String descripcion, long idCategoria) 
            
  {
     
    Ropa ropa = new Ropa(nombre, precio, color, marca, descripcion, idCategoria);
    //ropaDao.save(ropa);
    ropaDao.anadirRopa(ropa);
    return ropa;
   }
  
  public Adjunto registrarAdjunto (String nombre, Blob imagen,Ropa ropa) {
      Adjunto adjunto = new Adjunto (nombre, imagen, ropa);
      ropa.addAdjunto(adjunto);
      adjuntoDao.anadirAdjunto(adjunto);
      return adjunto;
  }
       
  public byte[] verImagen (long idRopa) {
      byte[] devolver = null;
              
              
              return devolver;

  }
       
  public Ropa registrarRopa (Ropa ropa)
  {
    ropaDao.anadirRopa(ropa);
    ropaDao.save(ropa);
    return ropa;
       
  }
       
       
  @Transactional(readOnly = true)
  public Ropa findRopa(Long idRopa) throws InstanceNotFoundException 
  {
    return ropaDao.find(idRopa);
  }

  @Transactional
  public List<Ropa> listaRopa()
  {   
    return ropaDao.listaRopa();
  }
       
  
    @Transactional
  public List<Ropa> listaRopaConImagen()
  {   
    return ropaDao.listaRopa();
  }
       
   public void borrarRopa(){

    }
   
    public Adjunto recuperarAdjunto (Long idRopa) throws InstanceNotFoundException {
    
    return adjuntoDao.findByIdRopa(idRopa);
    }
    public Blob recuperarImagen (Long idRopa)throws InstanceNotFoundException{
    
    return recuperarAdjunto(idRopa).getImagen();
    }
    
  
    
    
    
  public void actualizarRopa(long idRopa, String nombre, long precio,
            String color, String marca, String descripcion, int numPuntos)
               throws InstanceNotFoundException 
  {       
    Ropa ropa = ropaDao.find(idRopa);
    ropa.setNombre(nombre);
    ropa.setPrecio(precio);
    ropa.setColor(color);
    ropa.setMarca(marca);
    ropa.setDescripcion(descripcion);
    ropa.setNumPuntos(numPuntos);
 
   }
 
  
  
  
}

