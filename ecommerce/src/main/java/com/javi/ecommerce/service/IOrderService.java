package com.javi.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.javi.ecommerce.model.Order;
import com.javi.ecommerce.model.User;

public interface IOrderService {

	List<Order> findAll();
	Optional<Order> findById(Integer id);
	Order save (Order orden);
	 String generarNumeroOrden();
	 List<Order> findByUsuario(User usuario);
	 

}
