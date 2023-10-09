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

import com.javi.ecommerce.model.DetalleOrden;
import com.javi.ecommerce.model.Orden;
import com.javi.ecommerce.model.Producto;
import com.javi.ecommerce.model.Usuario;
import com.javi.ecommerce.service.IDetalleOrdenService;
import com.javi.ecommerce.service.IOrdenService;
import com.javi.ecommerce.service.IUsuarioService;
import com.javi.ecommerce.service.ProductoService;


@Controller
@RequestMapping("/")
public class HomeController {
	
	//variable para imprimir en consola en vez de print
	private final Logger log= LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	@Autowired
	private IDetalleOrdenService detalleOrdenService;
	
	//Almacenamos los detalles de la orden
	List<DetalleOrden> detalles= new ArrayList<DetalleOrden>();
	
	//Almacena los datos de la orden
	Orden orden = new Orden();
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return"usuario/home";	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		
		model.addAttribute("producto", producto);
		return "usuario/productoHome";
	}
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
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
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
		//quitar productos
		for(DetalleOrden detalleOrden: detalles) {
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
	public String getCart(Model model) {
		
		//mostrar en las vistas
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		return"/usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model) {
		
	
		Usuario usuario = usuarioService.findById(1).get();
		//mostrar en las vistas
				model.addAttribute("cart", detalles);
				model.addAttribute("orden", orden);
				model.addAttribute("usuario", usuario);
		
		return"usuario/resumenorden";
	}
	
	//Guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder() {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		Usuario usuario = usuarioService.findById(1).get();
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		//guardar detalles
		for(DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		//limpiar lista IOrden
		orden= new Orden();
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
	    List<Producto> productos = productoService
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
