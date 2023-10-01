/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.stocktalla;

import es.javi.tiendaonline.model.ropa.Ropa;
import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
    
/**
 *
 * @author emilio
 */
public interface StockTallaDao extends GenericDao<StockTalla, Long>{
     /**
     * Returns an StockTalla by StockTalla ( StockTalla identifier)
     *
     * @param idStockTalla the StockTalla identifier
     * @return the StockTalla
     */
  
      public StockTalla findByidStockTalla (int idStockTalla) throws InstanceNotFoundException;
      
       public StockTalla findByRopa (Ropa ropa) throws InstanceNotFoundException;
      
      public void anadirStockTalla (StockTalla stocktalla);
      
      public List<StockTalla> listaStockTalla();

   public List<StockTalla> listaStockTalla(long idRopa);
}
