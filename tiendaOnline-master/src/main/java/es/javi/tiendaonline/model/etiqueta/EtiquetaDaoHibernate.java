/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.etiqueta;

import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emilio
 */
@Repository("EtiquetaDao")
public class EtiquetaDaoHibernate extends
		GenericDaoHibernate<Etiqueta, Long> implements EtiquetaDao {


	public Etiqueta findByNombreEtiqueta(String nombreEtiqueta) throws InstanceNotFoundException {

    	Etiqueta etiqueta = (Etiqueta) getSession().createQuery(
    			"SELECT u FROM Etiqueta u WHERE u.nombreEtiqueta = :nombreEtiqueta")
    			.setParameter("nombreEtiqueta", nombreEtiqueta)
    			.uniqueResult();
    	if (etiqueta == null) {
   			throw new InstanceNotFoundException(nombreEtiqueta, Etiqueta.class.getName());
    	} else {
    		return etiqueta;
    	}
        
        }
	
        @Autowired
  private SessionFactory sessionFactory;
      
      public List<Etiqueta> listaEtiqueta() {
    
    Query query = getSession().createQuery("SELECT u FROM Etiqueta u");
    List<Etiqueta> lista= query.list();
    return lista;

  }
      public void anadirEtiqueta (Etiqueta etiqueta) {
      
      sessionFactory.getCurrentSession().save(etiqueta);
      
      }
}
