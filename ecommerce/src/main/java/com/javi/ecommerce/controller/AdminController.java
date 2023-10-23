package com.javi.ecommerce.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javi.ecommerce.model.Order;
import com.javi.ecommerce.model.Product;
import com.javi.ecommerce.service.IOrderService;
import com.javi.ecommerce.service.IUserService;
import com.javi.ecommerce.service.ProductService;



@Controller
@RequestMapping("/administrador")
public class AdminController {

	@Autowired
	private ProductService productoService;
	
	@Autowired
	private IUserService usuarioService;
	
	@Autowired
	private IOrderService ordenService;
	
	private Logger logg= LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("")
	public String home(Model model) { 
		List<Product> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		return"administrador/home";	}
	
	//Retorna los usuarios
	@GetMapping("/usuarios")
	public String usuarios(Model model){
		model.addAttribute("usuarios", usuarioService.findAll());
		return"administrador/usuarios";
		}


//Retorna las ordenes en panel de administrador
@GetMapping("/ordenes")
public String ordenes(Model model) {
	model.addAttribute("ordenes", ordenService.findAll());
	return"administrador/ordenes";
}

//ver detalle 
@GetMapping("/detalle/{id}")
public String detalle(Model model, @PathVariable Integer id) {
	logg.info("Id de la orden: {}", id);
	Order orden = ordenService.findById(id).get();
	
	model.addAttribute("detalles", orden.getDetalle());
	
	return"/administrador/detalleorden";

}
}
