/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.descuento;

import es.javi.tiendaonline.model.etiqueta.Etiqueta;
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
@Repository("DescuentoDao")
public class DescuentoDaoHibernate extends
		GenericDaoHibernate<Descuento, Long> implements DescuentoDao {
 
    
    
    	public Descuento findByPorcentajeDescuento(int porcentajeDescuento) throws InstanceNotFoundException {

    	Descuento descuento = (Descuento) getSession().createQuery(
    			"SELECT u FROM Descuento u WHERE u.porcentajeDescuento = :porcentajeDescuento")
    			.setParameter("porcentajeDescuento", porcentajeDescuento)
    			.uniqueResult();
    	if (descuento == null) {
   			throw new InstanceNotFoundException(porcentajeDescuento, Etiqueta.class.getName());
    	} else {
    		return descuento;
    	}
        
        }
        
        
        
          @Autowired
          private SessionFactory sessionFactory;
          
      
      public List<Descuento> listaDescuento() {
    
    Query query = getSession().createQuery("SELECT u FROM Descuento u");
    List<Descuento> lista= query.list();
    return lista;

  }
      
    public void anadirDescuento (Descuento descuento) {
    
     sessionFactory.getCurrentSession().save(descuento);
    
    
    }
}
