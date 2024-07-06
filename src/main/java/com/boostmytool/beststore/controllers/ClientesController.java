package com.boostmytool.beststore.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boostmytool.beststore.models.Cliente;
import com.boostmytool.beststore.models.ClienteDto;
import com.boostmytool.beststore.models.Product;
import com.boostmytool.beststore.models.ProductDto;
import com.boostmytool.beststore.services.ClientesRepository; // Asegúrate de tener esta interfaz

import jakarta.validation.Valid;

@Controller // Marca esta clase como un controlador de Spring MVC.
@RequestMapping("/clientes")// Establece la ruta base para todos los métodos de manejo de peticiones en esta clase.
public class ClientesController {

	@Autowired // Inyecta automáticamente la instancia de ClientesRepository.
	private ClientesRepository repo;

	@GetMapping({ "", "/" })
	public String showClientList(Model model) {
		// Consulta todos los clientes en la base de datos y los ordena por ID de forma descendente.
		List<Cliente> clientes = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		// Añade la lista de clientes al modelo para su uso en la vista.
		model.addAttribute("clientes", clientes);
		// Devuelve el nombre de la vista HTML que se mostrará.
		return "clientes/index"; // Ruta de la plantilla de vista para listar clientes.
	}

	@GetMapping("/create")
    public String showCreatePage(Model model) {
		// Prepara un nuevo DTO de Cliente para ser llenado en el formulario de creación.
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("clienteDto", clienteDto);
        return "clientes/CreateCliente"; // Vista para la creación de un nuevo cliente.
    }

    @PostMapping("/create")
    public String createCliente(
        @Valid @ModelAttribute("clienteDto") ClienteDto clienteDto,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
        	// Si hay errores en los datos del formulario, regresa a la vista de creación.
            return "clientes/CreateCliente";
        }
        
     // Crea una nueva entidad Cliente y la llena con datos del DTO.
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setCreatedAt(new Date()); // Asume que tienes este campo en Cliente

        repo.save(cliente);// Guarda el cliente en la base de datos.

        return "redirect:/clientes";// Redirecciona a la lista de clientes.
    }
    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
        	// Busca el cliente por ID y lo añade al modelo si existe.
            Cliente cliente = repo.findById(id).get();
            model.addAttribute("cliente", cliente);
            
            // Llena el DTO con datos del cliente para ser editados en el formulario.
            // Aquí puedes crear un objeto ClienteDto si es necesario para el formulario de edición
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setDireccion(cliente.getDireccion());
            clienteDto.setTelefono(cliente.getTelefono());
            clienteDto.setEmail(cliente.getEmail());

            model.addAttribute("clienteDto", clienteDto);
        } catch (Exception ex) {
        	// Maneja excepciones, por ejemplo, si el ID no existe.
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/clientes";// Redirecciona a la lista si hay un error.
        }
        
        // Vista para editar un cliente.
        return "clientes/EditCliente"; // Asegúrate de tener una vista llamada "EditCliente"
    }
    @PostMapping("/edit")
    public String updateCliente(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ClienteDto clienteDto,
            BindingResult result
    ) {
        try {
        	// Obtiene el cliente de la base de datos.
        	Cliente cliente = repo.findById(id).get();
  	        model.addAttribute("cliente", cliente);
  	        
            if (result.hasErrors()) {
            	// Si hay errores, permanece en la página de edición.
                return "clientes/EditCliente";
            }
            
            // Actualiza los datos del cliente con los nuevos valores del formulario.
            // Actualizar los atributos del cliente con los valores proporcionados en el ClienteDto
            cliente.setNombre(clienteDto.getNombre());
            cliente.setDireccion(clienteDto.getDireccion());
            cliente.setTelefono(clienteDto.getTelefono());
            cliente.setEmail(clienteDto.getEmail());

            // Guardar el cliente actualizado en la base de datos
            repo.save(cliente);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/clientes"; // Redirecciona a la lista de clientes.
    }
    @GetMapping("/delete")
    public String deleteCliente(
        @RequestParam int id
    ) {
        try {
        	 // Busca y obtiene el cliente a eliminar.
            Cliente cliente = repo.findById(id).get();
            
            // Elimina el cliente de la base de datos.
            repo.delete(cliente);
        } catch (Exception ex) {
        	 // Maneja posibles excepciones, como cliente no encontrado.
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/clientes";// Redirecciona a la lista de clientes tras eliminar.
    }


}
