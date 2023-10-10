package com.javi.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javi.ecommerce.model.Usuario;
import com.javi.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	//Obtener los log del controlador
	private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	//Usuario/registro
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuarioService.save(usuario);
		return"redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<Usuario> user=usuarioService.findByEmail(usuario.getEmail());
		//logger.info("Usuario de db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	//Obtener compras
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		
	//enviamos hacia la vista
	model.addAttribute("sesion", session.getAttribute("idusiario"));
		return"usuario/compras";
	}
}
