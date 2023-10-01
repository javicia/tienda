/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.novedad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import es.javi.tiendaonline.model.etiqueta.Etiqueta;

/**
 *
 * @author Emilio
 */
@Entity
public class Novedad extends Etiqueta  {
  
  private long idNovedad;
  private String esNovedad;
  private String fechaNovedad;
  
  
 @Column(name = "idNovedad")
@SequenceGenerator( // It only takes effect for
name = "idNovedadGenerator", // databases providing identifier
sequenceName = "NovedadSeq")
// generators.       
//@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "idNovedadGenerator")



    public long getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(long idNovedad) {
        this.idNovedad = idNovedad;
    }

    public String getEsNovedad() {
        return esNovedad;
    }

    public void setEsNovedad(String esNovedad) {
        this.esNovedad = esNovedad;
    }

    public String getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(String fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }
    
    
}
