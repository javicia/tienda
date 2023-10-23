package com.javi.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.javi.ecommerce.model.User;

public interface IUserService {
	
	List<User> findAll();
	Optional<User> findById(Integer id);
	User save(User usuario);
	Optional<User> findByEmail(String email);
	
}
