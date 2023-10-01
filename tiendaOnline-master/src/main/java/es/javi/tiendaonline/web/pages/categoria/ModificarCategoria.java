/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.categoria;

import es.javi.tiendaonline.model.categoria.Categoria;
import es.javi.tiendaonline.model.categoria.CategoriaDao;
import es.javi.tiendaonline.model.categoriaservice.CategoriaService;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class ModificarCategoria {

  @Property
  Categoria categoria;
  
   @Inject
   private CategoriaService categoriaService;
   
   
    
    public List<Categoria> getListaCategoria() {
    return categoriaService.listaCategoria();
  }


    
    public List <Categoria> getCategorias() {
    return categoriaService.listaCategoria();
    
    
    }
  
  
}
