/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.pages.categoria;

import es.javi.tiendaonline.model.categoria.Categoria;
import es.javi.tiendaonline.model.categoriaservice.CategoriaService;
import es.javi.tiendaonline.web.pages.Index;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Emilio
 */
public class VerCategoria {
  
  
  @Property
  private long idCategoria;
  
  @Property 
  private long idCategoria2;
  
  @Property
  private String nombreCategoria;
  @Property
  private long idCategoriaPadre;
  
  
      @SessionState(create=false)
  Categoria categoria;
  
  @Inject
  CategoriaService categoriaService;
  
  
  
  void onActivate (long idCategoria ) throws InstanceNotFoundException {
      categoria = categoriaService.findCategoria(idCategoria);
      
  }
   void onPrepareForRender()  {
     idCategoria2 = categoria.getIdCategoria();
     nombreCategoria = categoria.getNombreCategoria();
     idCategoriaPadre = categoria.getIdCategoriaPadre();
    }
   
   
    Object onSuccess() throws InstanceNotFoundException {
        categoriaService.actualizarCategoria(idCategoria2, nombreCategoria, 
                idCategoriaPadre);
        return Index.class;

    }
}
