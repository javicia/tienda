package com.javi.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javi.ecommerce.model.OrderDetail;
import com.javi.ecommerce.repository.IOrderDetailRepository;

@Service
public class OrderDetailService implements IOrderDetailService{

	@Autowired
	private IOrderDetailRepository detalleOrdenRepository;
	@Override
	public OrderDetail save(OrderDetail detalleOrden) {
		// TODO Auto-generated method stub
		return detalleOrdenRepository.save(detalleOrden);
	}

}
