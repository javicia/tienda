package com.javi.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javi.ecommerce.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>{

}
