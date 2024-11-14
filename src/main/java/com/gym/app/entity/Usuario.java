package com.gym.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")

public class Usuario {

	@Id
	private String id;

	
	private String usuario;
	private String contrasena;

	private String nombres;
	private String apellidos;
	private String documento;
	private String correo;
	private String telefono;
	private double peso;
	private double altura;
	
	
	
	public Usuario() {
		super();
	}
	
	public Usuario(String id, String usuario, String contrasena, String nombres, String apellidos, String documento,
			String correo, String telefono, double peso, double altura) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.documento = documento;
		this.correo = correo;
		this.telefono = telefono;
		this.peso = peso;
		this.altura = altura;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	
}
