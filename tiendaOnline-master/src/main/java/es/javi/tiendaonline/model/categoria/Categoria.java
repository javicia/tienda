
package es.javi.tiendaonline.model.categoria;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import es.javi.tiendaonline.model.ropa.Ropa;

/**
 *
 * @author emilio
 */
@Entity
public class Categoria {
    private long idCategoria;
    private String nombreCategoria;
    private long idCategoriaPadre;

    
    private List <Ropa> ropas;
    public Categoria(){};
    
    public Categoria(String nombreCategoria, int idCategoriaPadre) {
        this.nombreCategoria = nombreCategoria;
        this.idCategoriaPadre = idCategoriaPadre;
    }
    
    @Column(name = "idCategoria")
@SequenceGenerator( // It only takes effect for
name = "idCategoriaGenerator", // databases providing identifier
sequenceName = "CategoriaSeq")
// generators.       
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "idCategoriaGenerator")
    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public long getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(long idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
    }

  @OneToMany(mappedBy = "idCategoria")
  public List<Ropa> getRopas() {
    return ropas;
  }

  public void setRopas(List<Ropa> ropas) {
    this.ropas = ropas;
  }

  
  @Override
  public String toString() {
    return String.valueOf(this.getIdCategoria()); 
  }

    
    
    
    }
