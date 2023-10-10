package com.javi.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javi.ecommerce.model.Orden;
import com.javi.ecommerce.model.Usuario;
import com.javi.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return ordenRepository.save(orden);
	}

	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return ordenRepository.findAll();
	}
	
	//Generamos ordenes pasamos de enteros a string 
	public String generarNumeroOrden() {
		int numero = 0;
		String numeroConcatenado="";
		List<Orden> ordenes= findAll();
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
	public List<Orden> findByUsuario(Usuario usuario) {
	
		return ordenRepository.findByUsuario(usuario);
	}

}
