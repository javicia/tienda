/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.recomendacion;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emilio
 */
@Repository("RecomendacionDao")
public class RecomendacionDaoHibernate extends
	GenericDaoHibernate<Recomendacion, Long> implements RecomendacionDao {
         @Autowired
      private SessionFactory sessionFactory;
    
        public Recomendacion findByidRecomendacion(long idRecomendacion) 
                        throws InstanceNotFoundException{
            Recomendacion recomendacion = (Recomendacion) getSession().createQuery(
    			"SELECT u FROM Recomendacion u WHERE u.idRecomendacion = :idRecomendacion")
    			.setParameter("idRecomendacion", idRecomendacion)
    			.uniqueResult();
    	if (recomendacion == null) {
   			throw new InstanceNotFoundException(idRecomendacion, Recomendacion.class.getName());
    	} else {
    		return recomendacion;
    	}  
        
        }
    
          public Recomendacion findByIdsRopa (long idRopa1, long idRopa2) throws InstanceNotFoundException {
             Recomendacion recomendacion = (Recomendacion) getSession().createQuery(
    			"SELECT u FROM Recomendacion u WHERE (u.idRopa1 = :idRopa1) and (u.idRopa2 = :idRopa2)")
    			.setParameter("idRopa1", idRopa1).setParameter("idRopa2", idRopa2)
    			.uniqueResult();
    	if (recomendacion == null) {
   			throw new InstanceNotFoundException(idRopa1, Recomendacion.class.getName());
    	} else {
    		return recomendacion;
    	}  
        
            
            }
          
           public List<Recomendacion> findidRopa1(long idRopa1) throws InstanceNotFoundException {
             List<Recomendacion> recomendacion = (List<Recomendacion>)getSession().createQuery(
    			"SELECT u FROM Recomendacion u WHERE (u.idRopa1 = :idRopa1) ")
    			.setParameter("idRopa1", idRopa1).list();
    	if (recomendacion == null) {
   			throw new InstanceNotFoundException(idRopa1, Recomendacion.class.getName());
    	} else {
    		return recomendacion;
    	}  
        
            
            }
          
           public List<Recomendacion> findidRopa2(long idRopa2) throws InstanceNotFoundException {
             List<Recomendacion> recomendacion = (List<Recomendacion>)getSession().createQuery(
    			"SELECT u FROM Recomendacion u WHERE (u.idRopa2 = :idRopa2) ")
    			.setParameter("idRopa1", idRopa2);
    	if (recomendacion == null) {
   			throw new InstanceNotFoundException(idRopa2, Recomendacion.class.getName());
    	} else {
    		return recomendacion;
    	}  
        
            
            }
 

    public List<Long> listadeIds2 (long idRopa1) {
      List<Long> devolver;
      
           devolver = (List<Long>)getSession().createQuery(
    			"SELECT u.idRopa2 FROM Recomendacion u WHERE (u.idRopa1 = :idRopa1)")
    			.setParameter("idRopa1", idRopa1).list();
           return devolver;
        
    }
    
        public List<Long> listadeIds1 (long idRopa2) {
      List<Long> devolver;
           devolver = (List<Long>)getSession().createQuery(
    			"SELECT u.idRopa1 FROM Recomendacion u WHERE (u.idRopa2 = :idRopa2)")
    			.setParameter("idRopa2", idRopa2).list();
           return devolver;
        
    }
    // Lista todas las Recomendaciones con todas sus propiedades.  
       public List<Recomendacion> listaTodasRecomendaciones() {
    Query query = getSession().createQuery("SELECT u FROM Recomendacion u");
        List<Recomendacion> listaRecomendaciones= query.list();
        return listaRecomendaciones;
    
    
    
    }
        
       public void insertarRecomendacion (Recomendacion recomendacion) {
       
       sessionFactory.getCurrentSession().save(recomendacion);
       }
       
}
