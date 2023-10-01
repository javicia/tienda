

package es.javi.tiendaonline.model.novedad;

import es.javi.tiendaonline.model.descuento.*;
import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;


public interface NovedadDao extends GenericDao<Novedad, Long> {
      /**
     * Returns an Novedad by esNovedad ( not Novedad identifier)
     *
     * @param esNovedad the user identifier
     * @return the Novedad
     */
       public Novedad findByEsNovedad(String esNovedad) throws InstanceNotFoundException;
   
}
