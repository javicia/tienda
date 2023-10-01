/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.categoriaservice;

import es.javi.tiendaonline.model.categoria.Categoria;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;

/**
 *
 * @author Emilio
 */
public interface CategoriaService {
  
  public Categoria crearCategoria (String nombreCategoria, int idCategoriaPadre);
    
        public Categoria findCategoria(Long idCategoria)
            throws InstanceNotFoundException;
        
        
        
        public void borrarCategoria();
  public List<Categoria> listaCategoria();
  
    public void actualizarCategoria (long idCategoria, String nombreCategoria, 
            long idCategoriaPadre) throws InstanceNotFoundException;
}
