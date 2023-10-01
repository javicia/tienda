/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.javi.tiendaonline.model.adjunto;

import es.javi.tiendaonline.model.comentario.Comentario;
import es.javi.tiendaonline.model.ropa.Ropa;
import es.javi.tiendaonline.modelutil.dao.GenericDaoHibernate;
import es.javi.tiendaonline.modelutil.exceptions.InstanceNotFoundException;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.BlobByteArrayType;
import org.springframework.stereotype.Repository;
/**
 *
 * @author javier
 */
@Repository("AdjuntoDao")
public class AdjuntoDaoHibernate extends
		GenericDaoHibernate<Adjunto, Long> implements AdjuntoDao{
    
         @Autowired
      private SessionFactory sessionFactory;
     public Adjunto findByNombreAdjunto(String nombreAdjunto) throws InstanceNotFoundException {

    	Adjunto adjunto = (Adjunto) getSession().createQuery(
    			"SELECT u FROM Adjunto u WHERE u.nombreAdjunto = :nombreAdjunto")
    			.setParameter("nombreAdjunto", nombreAdjunto)
    			.uniqueResult();
    	if (adjunto == null) {
   			throw new InstanceNotFoundException(nombreAdjunto, Adjunto.class.getName());
    	} else {
    		return adjunto;
    	}

	}
     
          public Adjunto findByIdRopa(Long idRopa) throws InstanceNotFoundException {

    	Adjunto adjunto = (Adjunto) getSession().createQuery(
    			"SELECT u FROM Adjunto u WHERE u.ropa.idRopa = :idRopa")
    			.setParameter("idRopa", idRopa)
    			.uniqueResult();
    	if (adjunto == null) {
   			throw new InstanceNotFoundException(idRopa, Adjunto.class.getName());
    	} else {
    		return adjunto;
    	}

	}
     
     
     
         public void anadirAdjunto (Adjunto adjunto) {
    
      sessionFactory.getCurrentSession().save(adjunto);
      
    
    }
    
     
     public BlobByteArrayType imagenById (Long idRopa) {
         
         BlobByteArrayType blob1  =(BlobByteArrayType) getSession().createQuery(
    			"SELECT u.imagen FROM Adjunto u WHERE u.ropa.idRopa = :idRopa")
    			.setParameter("idRopa", idRopa)
    			.uniqueResult();
     
             return blob1;
         
     }
     
     
     
            public byte[] imagenById2 (Long idRopa) {
         
         byte[] blob1 =(byte[]) getSession().createQuery(
    			"SELECT u.imagen FROM Adjunto u WHERE u.ropa.idRopa = :idRopa")
    			.setParameter("idRopa", idRopa)
    			.uniqueResult();
           
             return blob1;
         
     }
          
          
          
          public String[] imagenById3 (Long idRopa) {
         
         String[] blob1 =(String[]) getSession().createQuery(
    			"SELECT u.imagen FROM Adjunto u WHERE u.ropa.idRopa = :idRopa")
    			.setParameter("idRopa", idRopa)
    			.uniqueResult();
           
             return blob1;
         
     }
        public List<Adjunto> listaAdjunto () {
       
      Query query = getSession().createQuery("SELECT a FROM Adjunto a");
      List<Adjunto> listaAdjunto= query.list();
      return listaAdjunto;
     
     
     }
     
     public List listaIndex () {
           // Query query2 = getSession().createQuery("SELECT u.nombre, u.precio FROM Ropa u, IN (u.adjuntos) AS r WHERE r.ropa.idRopa = : u.idRopa ");
           //   Query query = getSession().createQuery("SELECT a.ropa.nombre,a.ropa.descripcion,a.ropa.idRopa FROM Adjunto a");
             Query query2 = getSession().createQuery("SELECT u FROM Ropa u, IN (u.adjuntos) AS r WHERE r.ropa.idRopa = : u.idRopa ");
    // Query query = getSession().createQuery("SELECT a.imagen, r.nombre,r.descripcion,r.precio  FROM Adjunto a INNER join Ropa r on r.idRopa = a.idRopa");
     List lista = query2.list();
     return lista;
     }
     
     
          public List<Blob> listaIndexSoloImagen () {
           // Query query2 = getSession().createQuery("SELECT u.nombre, u.precio FROM Ropa u, IN (u.adjuntos) AS r WHERE r.ropa.idRopa = : u.idRopa ");
              Query query = getSession().createQuery("SELECT a FROM Adjunto a");
                     // Query query2 = getSession().createQuery("SELECT u FROM Ropa u, IN (u.adjuntos) AS r WHERE r.ropa.idRopa = : u.idRopa ");
    // Query query = getSession().createQuery("SELECT a.imagen, r.nombre,r.descripcion,r.precio  FROM Adjunto a INNER join Ropa r on r.idRopa = a.idRopa");
     List<Blob> lista = query.list();
     return lista;
     }
    
}
