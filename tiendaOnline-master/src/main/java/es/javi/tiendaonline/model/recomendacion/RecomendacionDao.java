/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.recomendacion;

import es.javi.tiendaonline.model.pedido.Pedido;
import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import java.util.List;

/**
 *
 * @author emilio
 */
public interface RecomendacionDao extends GenericDao<Recomendacion, Long> {

    /**
     * Returns an Recomendacion by idRecomendacion ( Recomendacion identifier)
     *
     * @param idRecomentadion the Recomendacion identifier
     * @return the Recomendacion
     */
    public Recomendacion findByidRecomendacion(long idRecomendacion) throws InstanceNotFoundException;
    
    public Recomendacion findByIdsRopa (long idRopa1, long idRopa2) throws InstanceNotFoundException;
    

    
     public List<Recomendacion> listaTodasRecomendaciones();
     
     public void insertarRecomendacion (Recomendacion recomendacion);
     public List<Long> listadeIds2 (long idRopa1);
     public List<Long> listadeIds1 (long idRopa2);
}
