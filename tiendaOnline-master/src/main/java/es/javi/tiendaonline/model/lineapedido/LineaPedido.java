/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.lineapedido;

import es.javi.tiendaonline.model.pedido.Pedido;
import es.javi.tiendaonline.model.stocktalla.StockTalla;

import javax.persistence.*;
/**
 *
 * @author emilio
 */
@Entity
public class LineaPedido {
  
    private long idLineaPedido;
    private long precioUnitario;
    private int numeroUnidades;
    
    private long idStockTalla;
    private Pedido pedido;
    private long idRopa;
    
    public LineaPedido() {}

    public LineaPedido(Pedido pedido, long idStockTalla, long idRopa,long precioUnitario, int numeroUnidades) {
        this.pedido = pedido;
        this.idStockTalla = idStockTalla;
        this.idRopa = idRopa;
        this.precioUnitario = precioUnitario;
        this.numeroUnidades = numeroUnidades;
    }
    
    @Column(name = "idLineaPedido")
    @SequenceGenerator( // It only takes effect for
    name = "idLineaPedidoGenerator", // databases providing identifier
    sequenceName = "LineaPedidoSeq")
    // generators.       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idLineaPedidoGenerator")
    public long getIdLineaPedido() {
        return idLineaPedido;
    }

    public void setIdLineaPedido(long idLineaPedido) {
        this.idLineaPedido = idLineaPedido;
    }

    public long getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }
    
    
  @ManyToOne(optional=false, fetch=FetchType.LAZY)
  @JoinColumn(name="idPedido")
  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }
  public long getIdRopa() {
    return idRopa;
  }

  public void setIdRopa(long idRopa) {
    this.idRopa = idRopa;
  }

  public long getIdStockTalla() {
    return idStockTalla;
  }

  public void setIdStockTalla(long idStockTalla) {
    this.idStockTalla = idStockTalla;
  }

 @Override
  public String toString() {
    return String.valueOf(this.getIdLineaPedido()); 
  }


  

 
    
    
    
}
