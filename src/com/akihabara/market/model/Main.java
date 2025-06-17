package com.akihabara.market.model;
import java.sql.*;
public class Main {
	public static void main(String[] args) {
		
		// Configurar la conexión a la base de datos
		String url = "jdbc:mysql://localhost:3306/akihabaradb";
		String usuario = "root";
		String contrasena = "curso";
		try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
			System.out.println("Conexión exitosa");
			
			// Crear un producto de prueba
			ProductoOtaku producto = new ProductoOtaku(17, "Llavero One Piece", "Llavero", 12.5, 20);
			
			// Insertar el producto en la base de datos
			String insertarProducto = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
			try(PreparedStatement preparedStatement = conexion.prepareStatement(insertarProducto, Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, producto.mostrarNombre());
				preparedStatement.setString(2, producto.mostrarCategoria());
				preparedStatement.setDouble(3,  producto.mostrarPrecio());
				preparedStatement.setInt(4,  producto.mostrarStock());
				int mostrarFilas = preparedStatement.executeUpdate();
				System.out.println("Comenzando la inserción");
				if(mostrarFilas > 0) {
					try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							producto.configurarID(generatedKeys.getInt(1));
						}
					}
					System.out.println("Producto insertado con éxito");
					System.out.println(producto);
				}
			}
			
			// Leer el producto desde la base de datos
			String seleccionarProducto = "SELECT * FROM productos WHERE id = ?";
			try(PreparedStatement preparedStatement = conexion.prepareStatement(seleccionarProducto)) {
				preparedStatement.setInt(1, producto.mostrarID());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						ProductoOtaku productoBase = new ProductoOtaku(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("categoria"),
								resultSet.getDouble("precio"),
								resultSet.getInt("stock")
								);
						System.out.println("Producto leído desde la base de datos");
						System.out.println(productoBase);
					}
				}
			}
		} catch(SQLException e) {
			System.out.println("Error al conectar con la base de datos: " + e.getMessage());
		}
	}
}