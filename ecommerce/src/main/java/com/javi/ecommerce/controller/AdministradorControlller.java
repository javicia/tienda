package com.javi.ecommerce.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javi.ecommerce.model.Producto;
import com.javi.ecommerce.service.IUsuarioService;
import com.javi.ecommerce.service.ProductoService;



@Controller
@RequestMapping("/administrador")
public class AdministradorControlller {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("")
	public String home(Model model) { 
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		return"administrador/home";	}
	
	//Retorna los usuarios
	@GetMapping("/usuarios")
	public String usuarios(Model model){
		model.addAttribute("usuarios", usuarioService.findAll());
		return"administrador/usuarios";
		}
}
