/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.etiquetaservice;

import es.javi.tiendaonline.model.etiqueta.Etiqueta;
import es.javi.tiendaonline.model.etiqueta.EtiquetaDao;
import es.javi.tiendaonline.model.stocktalla.StockTallaDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Emilio
 */
@Service("EtiquetaService")
@Transactional
public class EtiquetaServiceImpl implements EtiquetaService {
  
  @Autowired
  private EtiquetaDao etiquetaDao;
  
  @Autowired
  private StockTallaDao stockTallaDao;
  
   
   
     public Etiqueta registrarEtiqueta (String nombreEtiqueta) 
            {

         Etiqueta etiqueta = new Etiqueta(nombreEtiqueta);
         etiquetaDao.save(etiqueta);
  
         
         return etiqueta;
       }
       
       public Etiqueta registrarEtiqueta (Etiqueta etiqueta)
       {
       etiquetaDao.save(etiqueta);
       return etiqueta;
       
       }
       
       
       @Transactional(readOnly = true)
       public Etiqueta findEtiqueta(Long idEtiqueta) throws InstanceNotFoundException{
                 return etiquetaDao.find(idEtiqueta);
       
       }

        @Transactional(readOnly = true)
       public Etiqueta findEtiqueta(String nombreEtiqueta) throws InstanceNotFoundException{
                 return etiquetaDao.findByNombreEtiqueta(nombreEtiqueta);
       
       }
       @Transactional
       public List<Etiqueta> listaEtiqueta(){
       
       return etiquetaDao.listaEtiqueta();
       
       }
       
}
