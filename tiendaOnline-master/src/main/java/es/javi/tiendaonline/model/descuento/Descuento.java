/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.descuento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import es.javi.tiendaonline.model.etiqueta.Etiqueta;

/**
 *
 * @author Emilio
 */
@Entity
public class Descuento extends Etiqueta  {
  
  private long idDescuento;
  private long porcentajeDescuento;

  public Descuento() {
  }

  public Descuento( String nombreEtiqueta, long porcentajeDescuento) {
    super(nombreEtiqueta);
    this.porcentajeDescuento = porcentajeDescuento;
  }
  public Descuento (long porcentajeDescuento) {
    
    this.porcentajeDescuento = porcentajeDescuento;
  
  
  }
 /* 
 @Column(name = "idDescuento")
@SequenceGenerator( // It only takes effect for
name = "idDescuentoGenerator", // databases providing identifier
sequenceName = "DescuentoSeq")
// generators.       
//@Id
//@GeneratedValue(strategy = GenerationType.AUTO, generator = "idDescuentoGenerator") 
* 
* */
  public long getIdDescuento() {
    return idDescuento;
  }

  public void setIdDescuento(long idDescuento) {
    this.idDescuento = idDescuento;
  }

    public long getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(long porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

  @Override
  public String toString() {
    return String.valueOf(this.getIdDescuento());
  }
  
    
}
