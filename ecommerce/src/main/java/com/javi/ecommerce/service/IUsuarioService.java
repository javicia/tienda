package com.javi.ecommerce.service;

import java.util.Optional;

import com.javi.ecommerce.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> findById(Integer id);
	
	
}
