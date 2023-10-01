/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.categoria;

import es.javi.tiendaonline.model.categoria.Categoria;
import es.javi.tiendaonline.model.categoriaservice.CategoriaService;
import es.javi.tiendaonline.web.pages.Index;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;

import java.util.List;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

/**
 *
 * @author Emilio
 */
@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class CrearCategoria {
  
  @Property
    private String nombreCategoria;
    
 @Property
 private int idCategoriaPadre;

 
 
 @Component
    private Form crearCategoriaForm;
 
 @Inject
 private CategoriaService categoriaService;




void onValidateFromcrearCategoriaForm() 
        {

          if (!crearCategoriaForm.isValid()) 
          {
              return;
          }
              
          Categoria categoria = categoriaService.crearCategoria(nombreCategoria, idCategoriaPadre);               

        }

      Object onSuccess() {

        return Index.class;

    }
      
      @Property
private SelectModel categoriaSelectModel;

@Inject
SelectModelFactory selectModelFactory;


void setupRender() {
    // invoke my service to find all descuentos, e.g. in the database
    List<Categoria> categorias = categoriaService.listaCategoria();
 
    // create a SelectModel from my list of descuentos
    categoriaSelectModel = selectModelFactory.create(categorias, "nombreCategoria");
}



}