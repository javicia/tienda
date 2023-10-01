/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.stocktalla;

import javax.persistence.*;
import javax.persistence.SequenceGenerator;

import es.javi.tiendaonline.model.ropa.Ropa;

/**
 *
 * @author emilio
 */
@Entity
public class StockTalla {
   private long idStockTalla;
   private String talla;
   private int stock;
   
   
   private Ropa ropa;
   
   public StockTalla(){};
   
   public StockTalla(String talla, int stock, Ropa ropa) {
        this.talla = talla;
        this.stock = stock;
        this.ropa= ropa;
    }
@Column(name = "idStockTalla")
@SequenceGenerator( // It only takes effect for
name = "idStockTallaGenerator", // databases providing identifier
sequenceName = "StockTallaSeq")
// generators.       
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "idStockTallaGenerator")
    public long getIdStockTalla() {
        return idStockTalla;
    }

    public void setIdStockTalla(long idStockTalla) {
        this.idStockTalla = idStockTalla;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Non me interesa ter o stocktalla na ropa.
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="idRopa")
    public Ropa getRopa() {
        return ropa;
    }

    public void setRopa(Ropa ropa) {
        this.ropa = ropa;
    }
   
    
     @Override
  public String toString() {
    return String.valueOf(this.getIdStockTalla()); 
  }
   
   
   
   
   
}
