/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.adjuntoservice;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.ropa.Ropa;

import java.sql.Blob;
import java.util.List;

/**
 *
 * @author Emilio
 */
public interface AdjuntoService {
  
   public List<Adjunto> listaAdjuntos () ;
   public List listaRopaIndex ();
    public List<Blob> listaRopaIndexSoloImagen ();
}
