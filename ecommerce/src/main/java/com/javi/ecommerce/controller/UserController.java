package com.javi.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javi.ecommerce.model.Order;
import com.javi.ecommerce.model.User;
import com.javi.ecommerce.service.IOrderService;
import com.javi.ecommerce.service.IUserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UserController {

	//Obtener los log del controlador
	private final Logger logger= LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService usuarioService;
	
	@Autowired
	private IOrderService ordenService;
	
	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	//Usuario/registro
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(User usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword(passEncode.encode(usuario.getPassword()));
		usuarioService.save(usuario);
		return"redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@GetMapping("/acceder")
	public String acceder(User usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<User> user=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
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
	
	//Obtener compras.
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		
		//enviamos hacia la vista
		model.addAttribute("sesion", session.getAttribute("idusiario"));
		
		User usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<Order> ordenes = ordenService.findByUsuario(usuario);
		model.addAttribute("ordenes", ordenes);
		return"usuario/compras";
	}
	
	//Detalle de la orden
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la orden: {} ", id);
		Optional <Order> orden = ordenService.findById(id);
		
		model.addAttribute("detalles", orden.get().getDetalle());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return"usuario/detallecompra";
	}
	
	//cerrar sesión
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idusuario");
		
		return "redirect:/";
	}
}
