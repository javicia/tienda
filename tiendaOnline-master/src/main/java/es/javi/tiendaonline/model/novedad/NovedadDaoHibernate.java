/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.novedad;

import es.javi.tiendaonline.model.etiqueta.Etiqueta;
import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.springframework.stereotype.Repository;

/**
 *
 * @author emilio
 */
@Repository("NovedadDao")
public class NovedadDaoHibernate extends
		GenericDaoHibernate<Novedad, Long> implements NovedadDao {
 
    
    
    	public Novedad findByEsNovedad(String esNovedad) throws InstanceNotFoundException {

    	Novedad novedad = (Novedad) getSession().createQuery(
    			"SELECT u FROM Descuento u WHERE u.esNovedad = :esNovedad")
    			.setParameter("esNovedad", esNovedad)
    			.uniqueResult();
    	if (novedad == null) {
   			throw new InstanceNotFoundException(esNovedad, Etiqueta.class.getName());
    	} else {
    		return novedad;
    	}
        
        }
    
}
