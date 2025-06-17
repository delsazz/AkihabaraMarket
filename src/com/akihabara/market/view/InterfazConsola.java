package com.akihabara.market.view;
import java.util.List;
import java.util.Scanner;
import com.akihabara.market.dao.*;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.llm.LlmService;
@SuppressWarnings("unused")
public class InterfazConsola {
	
	// Crear un objeto privado para la clase Scanner
	private Scanner scanner;
	
	// Crear un constructor para la clase Scanner
	public InterfazConsola() {
		scanner = new Scanner(System.in);
	}
	
	// Crear un método para mostrar el menú principal
	public void menuPrincipal() {
		System.out.println("Menú principal");
		System.out.println("1. Añadir producto");
		System.out.println("2. Consuktar producto por ID ");
		System.out.println("3. Listar todos los productos");
		System.out.println("4. Listar productos por nombre");
		System.out.println("5. Listar productos por categoría");
		System.out.println("6. Actualizar producto");
		System.out.println("7. Eliminar producto");
		System.out.println("8. Salir");
		System.out.println("Introduce una opción para continuar: ");
	}
	
	// Crear un método para leer la opción introducida
	public int leerOpcion() {
		int opcion = -1;
		
		// Comprobar que el número introducido esté entre el 1 y el 8, sino lanzar mensaje de error
		while (opcion < 1 || opcion > 8) {
			try {
				opcion = Integer.parseInt(scanner.nextLine());
				if(opcion < 1 || opcion > 8) {
					System.out.println("Introduce un número entre el 1 y el 8");
				}
			} catch(NumberFormatException e) {
				System.out.println("Introduce un número entre el 1 y el 8");
			}
		}
		return opcion;
	}
	
	// Crear un método para introducir datos de un producto para insertarlo
	public ProductoOtaku datosProducto() {
		int id = 0;
		System.out.println("Introduce el tipo de producto: ");
		String tipo = scanner.nextLine();
		System.out.println("Introduce la franquicia: ");
		String franquicia = scanner.nextLine();
		
		// Construir prompt a partir del tipo y franquicia
		String prompt = "Sugiere un nombre llamativo y original para un producto otaku del tipo '" 
				+ tipo + "' basado en la franquicia '" + franquicia + "' con longitud máxima 200 caracteres, no pongas comillas y no expliques porqué has elegido ese nombre. Quiero una respuesta corta";
		
		// Aprovechar LlmService para proponer un nombre generado con IA.
		LlmService llmService = new LlmService();
		String nombreSugerido = llmService.sugerirNombreProducto(prompt);
		System.out.println("Respuesta del LLM: " + nombreSugerido);
		System.out.println("Introduce una letra (S o N) para llamar así al producto: ");
		String usarNombre = scanner.nextLine();
		String nombreFinal;
		if (usarNombre.equalsIgnoreCase("S")) {
			nombreFinal = nombreSugerido;
		} else {
			System.out.println("Introduce el nombre del producto: ");
			nombreFinal = scanner.nextLine();
		}
		System.out.println("Introduce la categoría del producto: ");
		String categoria = scanner.nextLine();
		double precio = setDouble("Introduce el precio del producto: ");
		int stock = setInt("Introduce el stock del producto: ");
		return new ProductoOtaku(id, nombreFinal, categoria, precio, stock);
	}
	
	// Crear un método para pedir el ID de un producto nuevo
	public int numeroID() {
		return setInt("Introduce el ID del producto: ");
	}
	
	// Crear un método para pedir un texto al usuario
	public String texto(String mensaje) {
		System.out.println(mensaje);
		return scanner.nextLine();
	}
	
	// Crear un método para pedir un número entero
	public int setInt(String mensaje) {
		while(true) {
			try {
				System.out.println(mensaje);
				return Integer.parseInt(scanner.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Error: Introduce un número válido");
			} 		
		}
	}
	
	// Crear un método para pedir un número decimal
	public double setDouble(String mensaje) {
		while(true) {
			try {
				System.out.println(mensaje);
				return Double.parseDouble(scanner.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Error: Introduce un nuémro decimal válido");
			}
		}
	}
	
	// Crear un método para mostrar un mensaje de información
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
	
	// Crear un método para mostrar un mensaje de error
	public void mostrarMensajeError(String mensaje) {
		System.out.println("Error: " + mensaje);
	}
	
	// Crear un método para mostrar una lisra con los productos
	public void mostrarListaProductos(List<ProductoOtaku> productos) {
		if(productos.isEmpty()) {
			System.out.println("No hay productos");
		} else {
			for(ProductoOtaku productoOtaku: productos) {
				System.out.println(productoOtaku);
			}
		}
	}
	
	// Crear un método para mostrar un solo producto
	public void mostrarProducto(ProductoOtaku productoOtaku) {
		if(productoOtaku != null) {
			System.out.println(productoOtaku);
		} else {
			System.out.println("Producto no encontrado");
		}
	}
}
