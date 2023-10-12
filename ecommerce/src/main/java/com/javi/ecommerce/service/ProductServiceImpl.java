package com.javi.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javi.ecommerce.model.Product;
import com.javi.ecommerce.repository.IProductRepository;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private IProductRepository productoRepository;

	@Override
	public Product save(Product producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Product> get(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void update(Product producto) {
		productoRepository.save(producto);
		
	}

	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);		
	}

	@Override
	public List<Product> findAll() {
		return productoRepository.findAll();
	}

}
