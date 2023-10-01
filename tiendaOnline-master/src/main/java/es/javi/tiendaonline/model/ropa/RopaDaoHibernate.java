/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.ropa;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Emilio
 */
@Repository("RopaDao")
public class RopaDaoHibernate extends
		GenericDaoHibernate<Ropa, Long> implements RopaDao { 
  

  public Ropa findByidRopa(long idRopa) throws InstanceNotFoundException {

    	Ropa ropa = (Ropa) getSession().createQuery(
    			"SELECT u FROM Ropa u WHERE u.idRopa = :idRopa")
    			.setParameter("idRopa", idRopa).uniqueResult();
    	if (ropa == null) {
   			throw new InstanceNotFoundException(idRopa, Ropa.class.getName());
    	} else {
    		return ropa;
    	}

        
	}

  
  @Autowired
  private SessionFactory sessionFactory;

  public List<Ropa> listaRopa() {
    
    Query query = getSession().createQuery("SELECT u FROM Ropa u");
    List<Ropa> lista= query.list();
    return lista;

  }
 
    public List<Ropa> listaRopaImagen() {
    
    Query query = getSession().createQuery("SELECT u FROM Ropa u WHERE ");
    List<Ropa> lista= query.list();
    return lista;

  }
  
  public void anadirRopa (Ropa ropa){
     sessionFactory.getCurrentSession().save(ropa);

      }
  
  
  
  
  
      
 }

  
  
  

