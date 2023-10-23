package com.javi.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javi.ecommerce.model.Product;
import com.javi.ecommerce.model.User;
import com.javi.ecommerce.service.IUserService;
import com.javi.ecommerce.service.ProductService;
import com.javi.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/productos")
public class ProductController { 

	
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productoService;
	
	@Autowired
	private IUserService usuarioService;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return"productos/show";	}
	
	@GetMapping("/create")
	public String create() {
		return"productos/create";	}
	
	@PostMapping("/save")
	public String save(Product producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("Este es el objeto producto {}", producto);
		User u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		producto.setUsuario(u);
		
		//imagen
		if(producto.getId()== null) {//cuando se crea un producto
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}else {
			
		}
		productoService.save(producto);
		return "redirect:/productos";	
		}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product producto= new Product();
		Optional <Product> optionalProducto= productoService.get(id); 
		producto = optionalProducto.get();
		
		LOGGER.info("Producto buscado:{}", producto);
		model.addAttribute("producto", producto);
		return"productos/edit";	
		}
	
	
	@PostMapping("/update")
	public String update(Product producto, @RequestParam("img") MultipartFile file) throws IOException {
		Product p = new Product();
		p= productoService.get(producto.getId()).get();
		
		//actualizar imagen
		if(file.isEmpty()) { // cuando editamos u n producto pero no cambiamos de imagen
			producto.setImagen(p.getImagen());
		}else {// cuando se edita también la imagen
			//eliminar cuando la imagen no sea la imagen por defecto
			if(!p.getImagen().equals("default.jgp")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
		
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		//eliminar imagen
		Product p = new Product();
		p=productoService.get(id).get();
		
		//eliminar cuandpo la imagen no sea la imagen por defecto
		if(!p.getImagen().equals("default.jgp")) {
			upload.deleteImage(p.getImagen());
		}
		
		productoService.delete(id);
		return "redirect:/productos";
	}
	
	
}
