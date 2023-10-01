

package es.javi.tiendaonline.model.puntosExtra;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;


public interface PuntosExtraDao extends GenericDao<PuntosExtra, Long> {
      /**
     * Returns an Descuento by porcentajeDescuento ( not Etiqueta identifier)
     *
     * @param numeroPuntosExtra the user identifier
     * @return the Descuento
     */
       public PuntosExtra findByNumeroPuntosExtra(int numeroPuntosExtra) throws InstanceNotFoundException;
   
}
