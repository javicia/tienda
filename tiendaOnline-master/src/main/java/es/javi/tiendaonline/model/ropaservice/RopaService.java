/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.ropaservice;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.sql.Blob;
import java.util.List;

/**
 *
 * @author emilio
 */
public interface RopaService {
    
    
        public Ropa registrarRopa (String nombre, int precio,
            String color, String marca, String descripcion, long idCategoria);
    
        public Ropa findRopa(Long idRopa)
            throws InstanceNotFoundException;
        
        public void actualizarRopa (long idRopa, String nombre, long precio,
            String color, String marca, String descripcion, int numPuntos) 
                throws InstanceNotFoundException;
        
        
        public List<Ropa> listaRopa();
        public Adjunto recuperarAdjunto (Long idRopa) throws InstanceNotFoundException;
        public void borrarRopa();
        public Adjunto registrarAdjunto (String nombre, Blob imagen,Ropa ropa);
          public byte[] verImagen (long idRopa);
          public Blob recuperarImagen (Long idRopa)throws InstanceNotFoundException;
          
            public List<Ropa> listaRopaConImagen();

}
