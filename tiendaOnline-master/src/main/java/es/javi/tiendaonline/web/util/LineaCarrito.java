/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.web.util;

import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.stocktalla.StockTalla;

/**
 *
 * @author Emilio
 */
public class LineaCarrito {
  
  private String nombreRopa;
  private long precio;
  private int cantidad = 1;
  private Ropa ropa;
  private long idStockTalla;
  

  
  public LineaCarrito(long precio, Ropa ropa, long idStockTalla) {
    this.precio = precio;
    this.ropa = ropa;
    this.idStockTalla = idStockTalla;
  }

  public long getPrecio() {
    return precio;
  }

  public void setPrecio(long precio) {
    this.precio = precio;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = this.cantidad + cantidad;
  }

  public Ropa getRopa() {
    return ropa;
  }

  public void setRopa(Ropa ropa) {
    this.ropa = ropa;
  }

  public String getNombreRopa() {
    return ropa.getNombre();
  }

  public void setNombreRopa(String nombreRopa) {
    this.nombreRopa = nombreRopa;
  }

  public long getIdStockTalla() {
    return idStockTalla;
  }

  public void setIdStockTalla(long idStockTalla) {
    this.idStockTalla = idStockTalla;
  }

 
  
  
  
}
