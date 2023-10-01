/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.puntosExtra;

import es.javi.tiendaonline.model.descuento.*;
import es.javi.tiendaonline.model.etiqueta.Etiqueta;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Emilio
 */
@Entity
public class PuntosExtra extends Etiqueta  {
  
  private long idPuntosExtra;
  private int numeroPuntosExtra;
  
  
 @Column(name = "idPuntosExtra")
@SequenceGenerator( // It only takes effect for
name = "idPuntosExtraGenerator", // databases providing identifier
sequenceName = "PuntosExtraSeq")
// generators.       
//@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "idPuntosExtraGenerator")

    public long getIdPuntosExtra() {
        return idPuntosExtra;
    }

    public void setIdPuntosExtra(long idPuntosExtra) {
        this.idPuntosExtra = idPuntosExtra;
    }

    public int getNumeroPuntosExtra() {
        return numeroPuntosExtra;
    }

    public void setNumeroPuntosExtra(int numeroPuntosExtra) {
        this.numeroPuntosExtra = numeroPuntosExtra;
    }
    
    
   }
 
