/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.stocktallaservice;

import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.stocktalla.StockTalla;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;

/**
 *
 * @author emilio
 */
public interface StockTallaService {
    
    
        public StockTalla registrarStockTalla (String talla, int stock, Ropa ropa);
    
        public StockTalla findStockTalla(Long idStockTalla)
            throws InstanceNotFoundException;
        /*
        public void actualizarRopa (String nombre, int precio,
            String color, String marca, String descripcion) 
                throws InstanceNotFoundException;
        
        
        */
        public StockTalla findStockTalla(Ropa ropa) throws InstanceNotFoundException;

        
        public List<StockTalla> listaStockTalla();
        public List<StockTalla> listaStockTalla(long idRopa);
        
       public void actualizarStock (long idStockTalla, int unidades) throws InstanceNotFoundException;

        

        
    
}
