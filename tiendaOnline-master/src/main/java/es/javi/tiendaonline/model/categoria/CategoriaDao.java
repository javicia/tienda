

package es.javi.tiendaonline.model.categoria;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;

/**
 *
 * @author emilio
 */
public interface CategoriaDao extends GenericDao<Categoria, Long>{
     /**
     * Returns an Categoria by nombreCategoria ( not identifier)
     *
     * @param nombreCategoria 
     * @return the Categoria
     */
  
      public Categoria findByNombreCategoria(String nombreCategoria) throws InstanceNotFoundException;
      
      /**
     * Returns an Categoria by idCategoria (  identifier)
     *
     * @param idCategoria 
     * @return the Categoria
     */
      public Categoria findByIdCategoria(long idCategoria) throws InstanceNotFoundException;

      public List<Categoria> listaCategoria();
      
      public void actualizarCategoria(Categoria categoria);
      


}
