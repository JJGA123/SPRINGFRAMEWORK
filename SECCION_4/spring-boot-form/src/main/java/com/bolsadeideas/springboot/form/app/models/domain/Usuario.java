package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;
import com.bolsadeideas.springboot.form.app.validation.Requerido;

public class Usuario {

	//@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{2}[-][A-Z]{1}")
	@IdentificadorRegex
	private String identificador;
	
	//@NotBlank(message = "El nombre no puede ser vacío.")
	@Requerido
	private String nombre;
	
	@NotBlank(message = "El apellido no puede ser vacío.")
	private String apellido;
	
	@NotBlank(message = "El username no puede ser vacío.")
	@Size(min =4,max = 12,message = "El campo solo recibe entre 4 y 12 caracteres.")
	private String username;
	
	@NotBlank(message = "El password no puede ser vacío.")
	@Size(min =4,max = 15,message = "El campo solo recibe entre 4 y 15 caracteres.")
	private String password;
	
	//@NotEmpty(message = "El email no puede ser vacío.")
	@Email(message = "Correo con formato incorrecto.")
	@Size(min =4,max = 20,message = "El campo solo recibe entre 4 y 20 caracteres.")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

}
