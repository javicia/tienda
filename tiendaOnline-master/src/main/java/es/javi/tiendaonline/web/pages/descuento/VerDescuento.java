/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.descuento;

import es.javi.tiendaonline.model.descuento.Descuento;
import es.javi.tiendaonline.model.descuentoservice.DescuentoService;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

/**
 *
 * @author Emilio
 */
public class VerDescuento {
  
  @Property
  Descuento descuento;
  
  @Property
  long idDescuento;
  
  @Inject
  DescuentoService descuentoService;
  
  
  void onActivate (long idDescuento) throws InstanceNotFoundException {
   descuento = descuentoService.findDescuento(idDescuento);
   
  }
  
  public List<Descuento> getListaDescuento () {
  
  return descuentoService.listaDescuento();

  }

@Property
private SelectModel descuentoSelectModel;

@Inject
SelectModelFactory selectModelFactory;


void setupRender() {
    // invoke my service to find all descuentos, e.g. in the database
    List<Descuento> descuentos = descuentoService.listaDescuento();
 
    // create a SelectModel from my list of descuentos
    descuentoSelectModel = selectModelFactory.create(descuentos, "nombreEtiqueta");
}

}
