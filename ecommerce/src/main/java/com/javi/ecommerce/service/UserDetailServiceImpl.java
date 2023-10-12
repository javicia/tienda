package com.javi.ecommerce.service;



import java.util.Optional;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import jakarta.servlet.http.HttpSession;



@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	@Lazy
	private IUserService usuarioService;
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	HttpSession session;
	
	private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Este es el username");
		Optional<com.javi.ecommerce.model.User> optionalUser=usuarioService.findByEmail(username);
		if (optionalUser.isPresent()) {
			log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
			session.setAttribute("idusuario", optionalUser.get().getId());
			com.javi.ecommerce.model.User usuario= optionalUser.get();
			return User.builder().username(usuario.getNombre()).password(usuario.getPassword()).roles(usuario.getTipo()).build();
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");			
		}
	}

}
