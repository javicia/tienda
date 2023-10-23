package com.javi.ecommerce.controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javi.ecommerce.model.OrderDetail;
import com.javi.ecommerce.model.Order;
import com.javi.ecommerce.model.Product;
import com.javi.ecommerce.model.User;
import com.javi.ecommerce.service.IOrderDetailService;
import com.javi.ecommerce.service.IOrderService;
import com.javi.ecommerce.service.IUserService;
import com.javi.ecommerce.service.ProductService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController {
	
	//variable para imprimir en consola en vez de print
	private final Logger log= LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductService productoService;
	
	@Autowired
	private IUserService usuarioService;
	
	@Autowired
	private IOrderService ordenService;
	
	@Autowired
	private IOrderDetailService detalleOrdenService;
	
	//Almacenamos los detalles de la orden
	List<OrderDetail> detalles= new ArrayList<OrderDetail>();
	
	//Almacena los datos de la orden
	Order orden = new Order();
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		log.info("Sesión del usuario: {}", session.getAttribute("idusuario"));
		model.addAttribute("productos", productoService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return"usuario/home";	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Product producto = new Product();
		Optional<Product> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		
		model.addAttribute("producto", producto);
		return "usuario/productoHome";
	}
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		OrderDetail detalleOrden = new OrderDetail();
		Product producto = new Product();
		double sumaTotal = 0;

		Optional<Product> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		
		//validar que le producto no se añada 2 veces
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}
	
	//Quitar producto de carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		//lista nueva de productos
		List<OrderDetail> ordenesNueva = new ArrayList<OrderDetail>();
		//quitar productos
		for(OrderDetail detalleOrden: detalles) {
			if(detalleOrden.getProducto().getId()!= id) {
				ordenesNueva.add(detalleOrden);
			}
		}
		//agrega a la nueva lista con los productos restantes
		detalles=ordenesNueva;
		
		double sumaTotal=0;
		//sumamos todos los productos que están en el carrito
				sumaTotal=detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
				
				orden.setTotal(sumaTotal);
				
				//mostrar en las vistas
				model.addAttribute("cart", detalles);
				model.addAttribute("orden", orden);
				
		return"usuario/carrito";
	}
	
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		//mostrar en las vistas
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return"/usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
	
	User usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		//mostrar en las vistas
				model.addAttribute("cart", detalles);
				model.addAttribute("orden", orden);
				model.addAttribute("usuario", usuario);
		
		return"usuario/resumenorden";
	}
	
	//Guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		User usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		//guardar detalles
		for(OrderDetail dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		//limpiar lista IOrden
		orden= new Order();
		detalles.clear();
		
		return"redirect:/";
	}
	
	//Búsqueda
	@PostMapping("/search")
	public String searchProducto(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto: {}", nombre);
		
		// Normaliza el texto de búsqueda para considerar mayúsculas, minúsculas y acentos
	    String normalizedNombre = Normalizer.normalize(nombre, Normalizer.Form.NFD)
	            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	            .toLowerCase();
	    
		//Obtiene los productos en una lista, hace un stream, un filter(función lamda), le pasamos el predicado(nombre)
		//Con una función anónima -> Utilizamos replaceAll para eliminar los caracteres diacríticos (acentos) del texto normalizado.
	    //
		//y nos lo devuelve en una lista(collect)
	    List<Product> productos = productoService
	            .findAll()
	            .stream()
	            .filter(p -> {
	                String normalizedProductName = Normalizer.normalize(p.getNombre(), Normalizer.Form.NFD)
	                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	                        .toLowerCase();
	                return normalizedProductName.contains(normalizedNombre);
	            })
	            .collect(Collectors.toList());

		//Enviamos hacia la vista +
	model.addAttribute("productos", productos);
	
		return"usuario/home";
	}
}
