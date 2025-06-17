package com.akihabara.market.model;
public class ProductoOtaku {
	private int id;
	private String nombre;
	private String categoria;
	private double precio;
	private int stock;
	
	// Crear un constructor
	public ProductoOtaku(int id, String nombre, String categoria, double precio, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	// Implementar getters y setters
	public int mostrarID() {
		return id;
	}
	public void configurarID(int id) {
		this.id = id;
	}
	public String mostrarNombre() {
		return nombre;
	}
	public void configurarNombre(String nombre) {
		this.nombre = nombre;
	}
	public String mostrarCategoria() {
		return categoria;
	}
	public void configurarCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double mostrarPrecio() {
		return precio;
	}
	public void configurarPrecio(double precio) {
		this.precio = precio;
	}
	public int mostrarStock() {
		return stock;
	}
	public void configurarStock(int stock) {
		this.stock = stock;
	}
	
	// Sobreescribir métodos
	@Override
	public String toString() {
		return "ID: " + id + "\n" +
			       "Nombre: " + nombre + "\n" +
			       "Categoría: " + categoria + "\n" +
			       "Precio: " + precio + "\n" +
			       "Stock: " + stock;
	}

	public void configurarNombre1(String mostrarCategoria) {
	}
}