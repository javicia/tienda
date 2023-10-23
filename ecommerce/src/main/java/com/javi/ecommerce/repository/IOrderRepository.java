package com.javi.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javi.ecommerce.model.Order;
import com.javi.ecommerce.model.User;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUsuario(User usuario);
	
}
