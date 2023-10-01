/********************* EMILIO DOMÍNGUEZ SACHEZ *********************/


package es.javi.tiendaonline.model.recomendacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import es.javi.tiendaonline.model.ropa.Ropa;

/**
 *
 * @author emilio
 */
@Entity
public class Recomendacion {
    private long idRecomendacion;
    private long idRopa1;
    private long idRopa2;
    private int numVeces;

    public Recomendacion(){};
    
    public Recomendacion(long idRopa1, long idRopa2, int numVeces) {
        this.idRopa1 = idRopa1;
        this.idRopa2 = idRopa2;
        this.numVeces = numVeces;
    }
    @Column(name = "idRecomendacion")
    @SequenceGenerator( // It only takes effect for
    name = "idRecomendacionGenerator", // databases providing identifier
    sequenceName = "RecomendacionSeq")
    // generators.       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idRecomendacionGenerator")
    public long getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(long idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

  public long getIdRopa1() {
    return idRopa1;
  }

  public void setIdRopa1(long idRopa1) {
    this.idRopa1 = idRopa1;
  }

  public long getIdRopa2() {
    return idRopa2;
  }

  public void setIdRopa2(long idRopa2) {
    this.idRopa2 = idRopa2;
  }


    public int getNumVeces() {
        return numVeces;
    }

    public void setNumVeces(int numVeces) {
        this.numVeces = numVeces;
    }
    
}
