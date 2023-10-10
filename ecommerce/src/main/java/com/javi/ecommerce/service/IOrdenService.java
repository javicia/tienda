package com.javi.ecommerce.service;

import java.util.List;

import com.javi.ecommerce.model.Orden;
import com.javi.ecommerce.model.Usuario;

public interface IOrdenService {

	List<Orden> findAll();
	Orden save (Orden orden);
	 String generarNumeroOrden();
	 List<Orden> findByUsuario(Usuario usuario);

}
