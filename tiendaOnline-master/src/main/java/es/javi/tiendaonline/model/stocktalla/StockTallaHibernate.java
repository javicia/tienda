/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.stocktalla;

import es.javi.tiendaonline.model.ropa.Ropa;
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
@Repository("StockTallaDao")
public class StockTallaHibernate extends
		GenericDaoHibernate<StockTalla, Long> implements StockTallaDao {

    public StockTalla findByidStockTalla (int idStockTalla) throws InstanceNotFoundException{
          	StockTalla stocktalla = (StockTalla) getSession().createQuery(
    			"SELECT u FROM StockTalla u WHERE u.idStockTalla = :idStockTalla")
    			.setParameter("idStockTalla", idStockTalla)
    			.uniqueResult();
    	if (stocktalla == null) {
   			throw new InstanceNotFoundException(idStockTalla, StockTalla.class.getName());
    	} else {
    		return stocktalla;
    	}
            
      }
    
    
      public StockTalla findByRopa (Ropa ropa) throws InstanceNotFoundException{
          	StockTalla stocktalla = (StockTalla) getSession().createQuery(
    			"SELECT u FROM StockTalla u WHERE u.ropa = :ropa")
    			.setParameter("ropa", ropa)
    			.uniqueResult();
    	if (stocktalla == null) {
   			throw new InstanceNotFoundException(ropa, StockTalla.class.getName());
    	} else {
    		return stocktalla;
    	}
            
      }
    
      @Autowired
  private SessionFactory sessionFactory;
      
      public List<StockTalla> listaStockTalla() {
    
    Query query = getSession().createQuery("SELECT u FROM StockTalla u");
    List<StockTalla> lista= query.list();
    return lista;

  }
    
            public List<StockTalla> listaStockTalla(long idRopa) {
    
    Query query = getSession().createQuery(
            "SELECT u FROM StockTalla u WHERE u.ropa.idRopa = :idRopa").setParameter("idRopa",idRopa);
    List<StockTalla> lista= query.list();
    return lista;

  }
      public void anadirStockTalla (StockTalla stocktalla){
     sessionFactory.getCurrentSession().save(stocktalla);

      }
    
    
}
