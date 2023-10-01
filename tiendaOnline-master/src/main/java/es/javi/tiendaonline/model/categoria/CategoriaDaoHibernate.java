package es.javi.tiendaonline.model.categoria;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emilio
 */
@Repository("CategoriaDao")
public class CategoriaDaoHibernate extends
		GenericDaoHibernate<Categoria, Long> implements CategoriaDao{
    
    
     public Categoria findByNombreCategoria(String nombreCategoria) throws InstanceNotFoundException {

    	Categoria categoria = (Categoria) getSession().createQuery(
    			"SELECT u FROM Categoria u WHERE u.nombreCategoria = :nombreCategoria")
    			.setParameter("nombreCategoria", nombreCategoria)
    			.uniqueResult();
    	if (categoria == null) {
   			throw new InstanceNotFoundException(nombreCategoria, Categoria.class.getName());
    	} else {
    		return categoria;
    	}

	}
     
   public Categoria findByIdCategoria(long idCategoria) throws InstanceNotFoundException {
   
   Categoria categoria = (Categoria) getSession().createQuery(
    			"SELECT u FROM Categoria u WHERE u.idCategoria = :idCategoria")
    			.setParameter("idCategoria", idCategoria)
    			.uniqueResult();
    	if (categoria == null) {
   			throw new InstanceNotFoundException(idCategoria, Categoria.class.getName());
    	} else {
    		return categoria;
    	}
   
   
   
   
   }

     
      public List<Categoria> listaCategoria() {
        Query query = getSession().createQuery("SELECT u FROM Categoria u");
        List<Categoria> lista= query.list();
        return lista;

      }
      
      public void actualizarCategoria (Categoria categoria) {
        
        
      }     
    
}
