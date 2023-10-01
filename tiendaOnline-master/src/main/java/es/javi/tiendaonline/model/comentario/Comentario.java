/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.comentario;

import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.model.userprofile.UserProfile;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author emilio
 */
@Entity
public class Comentario {
    private long idComentario;
    private String textoComentario;  
    private UserProfile usuario;
    private Ropa ropa;


    
    public Comentario(){};
    
   
    
    public Comentario(String textoComentario, UserProfile usuario, Ropa ropa) {
        this.textoComentario = textoComentario;
        this.usuario = usuario;
        this.ropa = ropa;
    }
    
    
    @Column(name = "idComentario")
    @SequenceGenerator( // It only takes effect for
    name = "idComentarioGenerator", // databases providing identifier
    sequenceName = "ComentarioSeq")
    // generators.       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idComentarioGenerator")
    public long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(long idComentario) {
        this.idComentario = idComentario;
    }

    
    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

 
    @OneToOne
   @JoinColumn(name="usrId")
    public UserProfile getUsuario() {
        return usuario;
    }

    public void setUsuario(UserProfile usuario) {
        this.usuario = usuario;
    }

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="idRopa")
    public Ropa getRopa() {
        return ropa;
    }
    
    

    public void setRopa(Ropa ropa) {
        this.ropa = ropa;
    }



 
    
    
}
