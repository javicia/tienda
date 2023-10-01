/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.adjuntoservice;

import es.javi.tiendaonline.model.adjunto.Adjunto;
import es.javi.tiendaonline.model.adjunto.AdjuntoDao;
import es.javi.tiendaonline.model.ropa.Ropa;

import java.sql.Blob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Emilio
 */

@Service("AdjuntoService")
@Transactional
public class AdjuntoServiceImpl implements AdjuntoService {
  
  @Autowired
  AdjuntoDao adjuntoDao;
  

  
  @Transactional
  public List<Adjunto> listaAdjuntos () {
    return adjuntoDao.listaAdjunto();
  
  }
 

    
  @Transactional
  public List listaRopaIndex () {
    return adjuntoDao.listaIndex();
  
  }
    @Transactional
  public List<Blob> listaRopaIndexSoloImagen () {
    return adjuntoDao.listaIndexSoloImagen();
  
  }
}
