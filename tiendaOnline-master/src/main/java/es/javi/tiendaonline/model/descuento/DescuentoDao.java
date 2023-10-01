

package es.javi.tiendaonline.model.descuento;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import java.util.List;


public interface DescuentoDao extends GenericDao<Descuento, Long> {
      /**
     * Returns an Descuento by porcentajeDescuento ( not Etiqueta identifier)
     *
     * @param porcentajeDescuento the user identifier
     * @return the Descuento
     */
       public Descuento findByPorcentajeDescuento(int porcentajeDescuento) throws InstanceNotFoundException;
       
       public List<Descuento> listaDescuento();
       
       public void anadirDescuento(Descuento descuento);
                
   
}
