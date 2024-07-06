package com.boostmytool.beststore.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Date;

public class ClienteDto {

    @NotEmpty(message = "El nombre es requerido.")
    private String nombre;

    @NotEmpty(message = "La dirección es obligatoria.")
    @Size(min = 10, max = 255, message = "La dirección debe tener entre 10 y 255 caracteres.")
    private String direccion;

    @NotEmpty(message = "El teléfono es requerido.")
    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 dígitos.")
    private String telefono;

    @NotEmpty(message = "El correo electrónico es requerido.")
    @Email(message = "El correo electrónico debe ser válido.")
    private String email;

    // Puedes decidir si los clientes deben proporcionar esta información o no.
    private Date createdAt;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}


   