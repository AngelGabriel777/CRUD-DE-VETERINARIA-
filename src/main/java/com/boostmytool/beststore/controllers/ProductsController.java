package com.boostmytool.beststore.controllers;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

//Importaciones necesarias para Spring, validación, manejo de archivos, etc.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boostmytool.beststore.models.Product;
import com.boostmytool.beststore.models.ProductDto;
import com.boostmytool.beststore.services.ProductsRepository;

import jakarta.validation.Valid;

@Controller  // Esta anotación indica que esta clase es un controlador en Spring MVC.
@RequestMapping("/products")  // Define la ruta base para todos los métodos de este controlador.
public class ProductsController {
	
    @Autowired  // Inyección de dependencia para el repositorio de productos.
	private ProductsRepository repo;
	
	@GetMapping({"","/"})
	public String showProductList (Model model) {
        // Recupera todos los productos ordenados por ID de manera descendente y los agrega al modelo.
		List<Product>products = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
		model.addAttribute("products",products);
		return "products/index";// Devuelve la vista que muestra la lista de productos.
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
        // Crea un DTO de producto vacío para vincular formulario de creación de producto.
		ProductDto productDto = new ProductDto();
	    model.addAttribute("productDto", productDto);
	    return "products/CreateProduct";// Devuelve la vista para crear un nuevo producto.
	}

	@PostMapping("/create")
	public String createProduct(
	    @Valid @ModelAttribute ProductDto productDto,
	    BindingResult result
	) {
        // Validación para asegurarse de que se haya proporcionado un archivo de imagen.
	    if (productDto.getImageFile().isEmpty()) {
	        result.addError(new FieldError("productDto", "archivo de imagen", "El archivo de imagen es requerido."));
	    }
        // Si hay errores en el formulario, se retorna a la página de creación.
	    if (result.hasErrors()) {
	    	return "products/CreateProduct";
	    }
	    
        // Proceso de guardado del archivo de imagen.
	    MultipartFile image = productDto.getImageFile();
	    Date createdAt = new Date();
	    String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

	    try {
	        String uploadDir = "public/images/";
	        Path uploadPath = Paths.get(uploadDir);

	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        try (InputStream inputStream = image.getInputStream()) {
	            Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
	                       StandardCopyOption.REPLACE_EXISTING);
	        }
	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	    }
        // Creación y guardado del objeto Producto.
	    Product product = new Product();
	    product.setName(productDto.getName());
	    product.setBrand(productDto.getBrand());
	    product.setCategory(productDto.getCategory());
	    product.setPrice(productDto.getPrice());
	    product.setDescription(productDto.getDescription());
	    product.setCreatedAt(createdAt);
	    product.setImageFileName(storageFileName);

	    repo.save(product);// Redirecciona a la lista de productos después de crear.

	    
	    return "redirect:/products";
	}
	
	@GetMapping("/edit")
	public String showEditPage(
	    Model model,
	    @RequestParam int id
	) {
        // Carga el producto desde la base de datos para editarlo.
	    try {
	        Product product = repo.findById(id).get();
	        model.addAttribute("product", product);
	        
            // Transfiere los datos del producto a un DTO para la edición.
	        ProductDto productDto = new ProductDto();
	        productDto.setName(product.getName());
	        productDto.setBrand(product.getBrand());
	        productDto.setCategory(product.getCategory());
	        productDto.setPrice(product.getPrice());
	        productDto.setDescription(product.getDescription());

	        model.addAttribute("productDto", productDto);
	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	        return "redirect:/products";// Redirecciona si ocurre un error.
	    }

	    return "products/EditProduct"; // Devuelve la vista para editar el producto.
	}
	
	@PostMapping("/edit")
	public String updateProduct(
	    Model model,
	    @RequestParam int id,
	    @Valid @ModelAttribute ProductDto productDto,
	    BindingResult result
	) {
		// Proceso de actualización del producto.
	    try {
	        Product product = repo.findById(id).get();
	        model.addAttribute("product", product);
	     // Revisa errores de validación.
	        if (result.hasErrors()) {
	            return "products/editProduct";
	        }
	     // Gestión del cambio de archivo de imagen.
	        if (!productDto.getImageFile().isEmpty()) {
	        	// Elimina la imagen antigua.
	            // delete old image
	            String uploadDir = "public/images/";
	            Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

	            try {
	                Files.delete(oldImagePath);
	            } catch(Exception ex) {
	                System.out.println("Exception: " + ex.getMessage());
	            }

	          // Guarda el nuevo archivo de imagen.
	            MultipartFile image = productDto.getImageFile();
	            Date createdAt = new Date();
	            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

	            try (InputStream inputStream = image.getInputStream()) {
	                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
	            }

	            product.setImageFileName(storageFileName);
	        }
	        
            // Actualiza los datos del producto en la base de datos.
	        product.setName(productDto.getName());
	        product.setBrand(productDto.getBrand());
	        product.setCategory(productDto.getCategory());
	        product.setPrice(productDto.getPrice());
	        product.setDescription(productDto.getDescription());

	        repo.save(product);// Redirecciona a la lista de productos después de actualizar.
	    } 
	    catch(Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	    }

	    return "redirect:/products";
	}

	@GetMapping("/delete")
	public String deleteProduct(
	    @RequestParam int id
	) {
		// Proceso para eliminar un producto.
	    try {
	        Product product = repo.findById(id).get();
	        
	     // Elimina la imagen del producto.
	        Path imagePath = Paths.get("public/images/" + product.getImageFileName());

	        try {
	            Files.delete(imagePath);
	        } catch (Exception ex) {
	            System.out.println("Exception: " + ex.getMessage());
	        }
	        
	     // Elimina el producto de la base de datos.
	        repo.delete(product);
	    }
	    catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	    }
	    return "redirect:/products"; // Redirecciona después de eliminar el producto.
	}



}
