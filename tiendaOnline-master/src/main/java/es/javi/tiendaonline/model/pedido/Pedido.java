/********************* EMILIO DOMÍNGUEZ SACHEZ *********************/
package es.javi.tiendaonline.model.pedido;

import es.javi.tiendaonline.model.lineapedido.LineaPedido;
import es.javi.tiendaonline.model.userprofile.UserProfile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author emilio
 */
@Entity
public class Pedido {
  
    private long idPedido;
    private Date fechaPedido;
    private long precioTotal;    
    private UserProfile usuario;
    private String estado;  
    private List<LineaPedido> lineaPedidos = new ArrayList <LineaPedido>();

    public Pedido(){};
      


    public Pedido(Date fechaPedido, long precioTotal, UserProfile usuario, String estado) {
      	/**
		 * NOTE: "idPedido" *must* be left as "null" since its value is
		 * automatically generated.
		 */
        this.fechaPedido = fechaPedido;
        this.precioTotal = precioTotal;
        this.usuario = usuario;
        this.estado = estado;
    }
    
@Column(name = "idPedido")
@SequenceGenerator( // It only takes effect for
name = "idPedidoGenerator", // databases providing identifier
sequenceName = "PedidoSeq")
// generators.       
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "idPedidoGenerator")
    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public long getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(long precioTotal) {
        this.precioTotal = precioTotal;
    }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

    
    
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="usrId")
    public UserProfile getUsuario() {
        return usuario;
    }

    public void setUsuario(UserProfile usuario) {
        this.usuario = usuario;
    }
    
    @OneToMany(mappedBy = "pedido",fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT) //añadido para poder tener varios fetch 
    public List<LineaPedido> getLineaPedidos() {
        return lineaPedidos;
    }

    public void setLineaPedidos(List<LineaPedido> lineaPedidos) {
        this.lineaPedidos = lineaPedidos;
    }
    
    
    public void addLineaPedido (LineaPedido lineaPedido) {
    
    lineaPedidos.add(lineaPedido);
    
    
    }
    
    
}
