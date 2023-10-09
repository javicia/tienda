package com.javi.ecommerce.service;

import java.util.List;

import com.javi.ecommerce.model.Orden;

public interface IOrdenService {

	List<Orden> findAll();
	Orden save (Orden orden);
	 String generarNumeroOrden();
}
