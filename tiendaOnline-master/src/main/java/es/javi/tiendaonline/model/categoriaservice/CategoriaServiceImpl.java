/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.categoriaservice;

import es.javi.tiendaonline.model.categoria.Categoria;
import es.javi.tiendaonline.model.categoria.CategoriaDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Emilio
 */
@Service("CategoriaService")
@Transactional

public class CategoriaServiceImpl implements CategoriaService {
  
  @Autowired
  private CategoriaDao categoriaDao;
  
  public Categoria crearCategoria (String nombreCategoria, int idCategoriaPadre) {
  
    Categoria categoria = new Categoria (nombreCategoria, idCategoriaPadre);
    categoriaDao.save(categoria);
    return categoria;
  
  
  
  }
    
               @Transactional(readOnly = true)
            public Categoria findCategoria(Long idCategoria)
            throws InstanceNotFoundException {
        return categoriaDao.find(idCategoria);
        
        }
        
        
        
        
        public void borrarCategoria() {
        
        
        
        }
        
         @Transactional
       public List<Categoria> listaCategoria(){
       
       return categoriaDao.listaCategoria();
       
       }
         public void actualizarCategoria (long idCategoria, String nombreCategoria, 
            long idCategoriaPadre) throws InstanceNotFoundException  {
         
           Categoria categoria = categoriaDao.find(idCategoria);
           categoria.setNombreCategoria(nombreCategoria);
           categoria.setIdCategoriaPadre(idCategoriaPadre);
         
         }
  
  
}
