/*
 */
package es.javi.tiendaonline.model.etiqueta;

import java.util.List;
import javax.persistence.*;

import es.javi.tiendaonline.model.ropa.Ropa;

/**
 *
 * @author Emilio
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Etiqueta {
  private long idEtiqueta;
  private String nombreEtiqueta;
  
 private List<Ropa> ropa;
  
  public Etiqueta(){};

  public Etiqueta(String nombreEtiqueta) {
    this.nombreEtiqueta = nombreEtiqueta;
  }
  
  
  
@Column(name = "idEtiqueta")
@SequenceGenerator( // It only takes effect for
name = "idEtiquetaGenerator", // databases providing identifier
sequenceName = "EtiquetaSeq")
// generators.       
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "idEtiquetaGenerator")
  public long getIdEtiqueta() {
    return idEtiqueta;
  }

  public void setIdEtiqueta(long idEtiqueta) {
    this.idEtiqueta = idEtiqueta;
  }

  public String getNombreEtiqueta() {
    return nombreEtiqueta;
  }

  public void setNombreEtiqueta(String nombreEtiqueta) {
    this.nombreEtiqueta = nombreEtiqueta;
  }

   @ManyToMany(mappedBy = "etiquetas")
    public List<Ropa> getRopa() {
        return ropa;
    }

    public void setRopa(List<Ropa> ropa) {
        this.ropa = ropa;
    }

  @Override
  public String toString() {
    return String.valueOf(this.getIdEtiqueta());
  }
  
 
}
