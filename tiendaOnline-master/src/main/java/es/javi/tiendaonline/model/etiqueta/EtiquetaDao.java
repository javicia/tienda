
package es.javi.tiendaonline.model.etiqueta;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;

/**
 *
 * @author Emilio
 */
public interface EtiquetaDao extends GenericDao<Etiqueta, Long>{
  
  
  
   /**
     * Returns an Etiqueta by nombreEtiqueta ( not Etiqueta identifier)
     *
     * @param nombreEtiqueta the user identifier
     * @return the Etiqueta
     */
  
  public Etiqueta findByNombreEtiqueta(String nombreEtiqueta) throws InstanceNotFoundException;
  public List<Etiqueta> listaEtiqueta();
  
  public void anadirEtiqueta (Etiqueta etiqueta);
}
