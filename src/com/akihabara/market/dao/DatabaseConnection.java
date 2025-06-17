package com.akihabara.market.dao;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	
	// Crear las variables de conexión a la base de datos
	private static final String url = "jdbc:mysql://localhost:3306/akihabaradb";
	private static final String usuario = "root";
	private static final String contrasena = "curso";
	
	// Crear la propiedad privada conexión
	private Connection conexion;
	
	// Implementar un constructor
	public DatabaseConnection() {
		
		// Establecer la conexión a la base de datos
		try {
			this.conexion = DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Conexión exitosa");
		} catch(SQLException e) {
			System.out.println("Error al establecer la conexión: " + e.getMessage());
		  }
		}
		
		// Crear un método para obtener la conexión con la base de datos
		public Connection getConexion() {
			return conexion;
		}
		
		// Crar un método para cerrar la conexion
		public void cerrarConexion() {
			if(conexion != null) {
				try {
					conexion.close();
					System.out.println("Conexión cerrada");
				} catch(SQLException e) {
					System.out.println("Error al cerrar la conexión con la base de datos: " + e.getMessage());
				}
			}
		}	
    }