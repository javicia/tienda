package com.javi.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javi.ecommerce.model.Order;
import com.javi.ecommerce.model.User;
import com.javi.ecommerce.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private IOrderRepository ordenRepository;
	
	@Override
	public Order save(Order orden) {
		// TODO Auto-generated method stub
		return ordenRepository.save(orden);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return ordenRepository.findAll();
	}
	
	//Generamos ordenes pasamos de enteros a string 
	public String generarNumeroOrden() {
		int numero = 0;
		String numeroConcatenado="";
		List<Order> ordenes= findAll();
		List<Integer> numeros = new ArrayList<Integer>();
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
		if(ordenes.isEmpty()) {
			numero= 1;
		}else {
			numero=numeros.stream().max(Integer::compare).get();//Obtenemos el mayor número de la lista
			numero++;
		}
		if(numero<10) {
			numeroConcatenado="000000000" + String.valueOf(numero);
		}else if(numero <100) {
			numeroConcatenado="00000000" + String.valueOf(numero);
		}else if(numero <1000) {
			numeroConcatenado="0000000" + String.valueOf(numero);
		}else if(numero <10000) {
			numeroConcatenado="0000000" + String.valueOf(numero);
		}
		return numeroConcatenado;
	}

	@Override
	public List<Order> findByUsuario(User usuario) {
	
		return ordenRepository.findByUsuario(usuario);
	}

	@Override
	public Optional<Order> findById(Integer id) {
		// TODO Auto-generated method stub
		return ordenRepository.findById(id);
	}

}
