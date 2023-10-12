package com.javi.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.javi.ecommerce.model.Product;

public interface ProductService {
	public Product save(Product producto);
	public Optional<Product> get(Integer id);
	public void update(Product producto);
	public void delete(Integer id);
	public List<Product> findAll();
}
