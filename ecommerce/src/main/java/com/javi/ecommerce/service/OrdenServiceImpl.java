package com.javi.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javi.ecommerce.model.Orden;
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

}
