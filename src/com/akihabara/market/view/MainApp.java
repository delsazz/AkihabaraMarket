package com.akihabara.market.view;
import com.akihabara.market.view.InterfazConsola;
import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.dao.DatabaseConnection;
import java.util.List;
@SuppressWarnings("unused")
public class MainApp {
	public static void main(String[] args) {
		
		// Crear un objeto para las clases InterfazConsola y ProductoDAO
		InterfazConsola interfazConsola = new InterfazConsola();
		DatabaseConnection databaseConnection = new DatabaseConnection();
		ProductoDAO productoDAO = new ProductoDAO(databaseConnection);
		
		// Crear el menú para introducir las opciones y elegir
		int opcion;
		do {
			
			// Mostrar el menú principal
			interfazConsola.menuPrincipal();
		
			// Leer la opción introducida por el usuario
			opcion = interfazConsola.leerOpcion();
			switch(opcion) {
			
			// Añadir un nuevo producto
			case 1:
				ProductoOtaku nuevoProductoOtaku = interfazConsola.datosProducto();
				productoDAO.agregarProducto(nuevoProductoOtaku);
				interfazConsola.mostrarMensaje("Producto agergado");
				break;
				
			// Consultar un producto por ID
			case 2:
				int verID = interfazConsola.numeroID();
				ProductoOtaku productoOtakuConsultado = productoDAO.obtenerProductoPorId(verID);
				interfazConsola.datosProducto();
				break;
				
			case 3:
				
				// Mostrar todos los productos
				List<ProductoOtaku> productos = productoDAO.obtenerTodosLosProductos();
				interfazConsola.mostrarListaProductos(productos);
				break;
				
			case 4:
				
				// Hacer una lista de productos según su nombre
				String buscarPorNombre = interfazConsola.texto("Introduce el nombre del producto: ");
				List<ProductoOtaku> productosPorNombre = productoDAO.buscarProductosPorNombre(buscarPorNombre);
				interfazConsola.mostrarListaProductos(productosPorNombre);
				break;
				
			case 5:
				
				// Hacer uan lista de productos por categoría
				String buscarPorCategoria = interfazConsola.texto("Introduce la categoría para buscar sus productos: ");
				List<ProductoOtaku> productosPorCategoria = productoDAO.buscarProductoPorCategoria(buscarPorCategoria);
				interfazConsola.mostrarListaProductos(productosPorCategoria);
				break;
				
			case 6:
				
				// Actualizar un producto
				int actualizarId = interfazConsola.numeroID();
				ProductoOtaku productoOtakuEnBase = productoDAO.obtenerProductoPorId(actualizarId);
				if(productoOtakuEnBase != null) {
					ProductoOtaku productoOtakuActualizado = interfazConsola.datosProducto();
					productoOtakuEnBase.configurarNombre(productoOtakuActualizado.mostrarNombre());
					productoOtakuEnBase.configurarCategoria(productoOtakuActualizado.mostrarCategoria());
					productoOtakuEnBase.configurarPrecio(productoOtakuActualizado.mostrarPrecio());
					productoOtakuEnBase.configurarStock(productoOtakuActualizado.mostrarStock());	
					productoDAO.actualizarProducto(productoOtakuEnBase);
					System.out.println("Producto actualizado");
				} else {
					interfazConsola.mostrarMensajeError("Producto no encontrado");
				}
				break;
			case 7:
				
				// Eliminar un producto
				int eliminarProductoPorId = interfazConsola.numeroID();
				boolean productoOtakuEliminado = productoDAO.eliminarProducto(eliminarProductoPorId);
				if(productoOtakuEliminado) {
					interfazConsola.mostrarMensaje("Producto eliminado con éxito");
				} else {
					interfazConsola.mostrarMensajeError("Producto no encontrado o no se ha podido eliminar");
				}
				break;
			case 8:
				
				// Salir del programa
				interfazConsola.mostrarMensaje("Adiós");
				break;
				
			default:
				interfazConsola.mostrarMensajeError("Opción incorrecta, intenta otra vez");
				break;				
			}
		} while(opcion != 8);
		
		// Continuar con el bucle hasta que el usuario pulse 8	
	}
}