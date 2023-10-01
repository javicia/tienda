/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Emilio
 */
public class Carrito {
  
  @Property
  private List productos = null;

  
  public Carrito() {
    productos = new ArrayList ();
  }


  /*AÑADO UNA LINEA AL CARRITO NO PERSISTENTE
   * SI EL CARRITO YA TIENE ESE IDSTOCKTALLA LO QUE HACE ES SUMARLE UNO
   * A LA CANTIDAD
   */
  public void anadirProducto (LineaCarrito lineaCarrito) {
    long idsto = lineaCarrito.getIdStockTalla();
 
    if (buscarCarrito(idsto) != null ) {
        buscarCarrito(idsto).setCantidad(1);
    }
    else { productos.add(lineaCarrito);
    
          
    }
  }
    
    
    public long calculaPrecio () {
    
    long precio= 0;
    long precio2= 0;
    Iterator i = productos.iterator();
    LineaCarrito lineaCarrito = null;
        while (i.hasNext()) {
    
      
       LineaCarrito linea = (LineaCarrito)i.next();
       
      precio2 = linea.getPrecio() * linea.getCantidad();
      precio = precio + precio2;
    
      }
  
    return precio;
  } 
    
    
    
  
  
  
  public LineaCarrito buscarCarrito (long idStockTalla) {
    
  Iterator i = productos.iterator();
  LineaCarrito lineaCarrito = null;
  while (i.hasNext()) {
    
      
       LineaCarrito linea = (LineaCarrito)i.next();
       
       if (linea.getIdStockTalla() == idStockTalla)
          {
            lineaCarrito = linea;
            break;
        }
    
    }
  
    return lineaCarrito;
  } 

  public List getProductos() {
    return productos;
  }

  public void setProductos(List productos) {
    this.productos = productos;
  }

  
  public void vaciarCarrito () {
  
  this.productos.clear();
  
  }

  
}
