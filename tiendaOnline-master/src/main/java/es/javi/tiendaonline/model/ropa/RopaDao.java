/********************* EMILIO DOMÍNGUEZ SACHEZ *********************/

package es.javi.tiendaonline.model.ropa;


import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;

/**
 *
 * @author Emilio
 */
public interface RopaDao extends GenericDao<Ropa, Long>{ 
  
  
  
   /**
     * Returns an Ropa by idRopa ( Ropa identifier)
     *
     * @param idRopa the user identifier
     * @return the Ropa
     */
  
      public Ropa findByidRopa(long idRopa) throws InstanceNotFoundException;
      
      public List<Ropa> listaRopa();
      
      public void anadirRopa (Ropa ropa);

  
  
}
