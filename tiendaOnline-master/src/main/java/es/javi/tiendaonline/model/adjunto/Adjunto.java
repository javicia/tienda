/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.javi.tiendaonline.model.adjunto;

import java.sql.Blob;
import javax.persistence.*;

import es.javi.tiendaonline.model.ropa.Ropa;

/**
 *
 * @author javier
 */
@Entity
public class Adjunto  {
  
  
    private long idAdjunto;
    private String nombreAdjunto;
    private Blob imagen;
    private Ropa ropa;
    

    public Adjunto(){};
    public Adjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public Adjunto(String nombreAdjunto, Blob imagen, Ropa ropa) {
        this.nombreAdjunto = nombreAdjunto;
        this.imagen = imagen;
        this.ropa = ropa;
    }

    @Column(name = "idAdjunto")
    @SequenceGenerator( // It only takes effect for
    name = "idAdjuntoGenerator", // databases providing identifier
    sequenceName = "AdjuntoSeq")
    // generators.       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idAdjuntoGenerator")

    public long getIdAdjunto() {
        return idAdjunto;
    }

    public void setIdAdjunto(long idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

 @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="idRopa")
    public Ropa getRopa() {
    return ropa;
  }

  public void setRopa(Ropa ropa) {
    this.ropa = ropa;
  }

  @Lob
  @Column (name="imagen")
    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    
    
}
