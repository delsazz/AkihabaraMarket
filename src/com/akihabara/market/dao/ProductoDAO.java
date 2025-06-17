package com.akihabara.market.dao;
import com.akihabara.market.model.ProductoOtaku;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductoDAO {
    
    // Crear una propiedad privada de tipo DatabaseConnection
    private DatabaseConnection databaseConnection;
    
    // Crear un constructor para obtener la conexión con la base de datos y crear un elemento de ella
    public ProductoDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    // Crear un método para agregar un producto a la base de datos
    public void agregarProducto(ProductoOtaku producto) {
        String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
        Connection conexion = databaseConnection.getConexion();
        try (
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.mostrarNombre());
            preparedStatement.setString(2, producto.mostrarCategoria());
            preparedStatement.setDouble(3, producto.mostrarPrecio());
            preparedStatement.setInt(4, producto.mostrarStock());
            preparedStatement.executeUpdate();
            System.out.println("Producto añadido");
        } catch (SQLException e) {
            System.out.println("Error al añadir producto: " + e.getMessage());
        }
    }
    
    // Crear un método para buscar un producto de la base de datos según su ID 
    public ProductoOtaku obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Connection conexion = databaseConnection.getConexion();
        try (
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new ProductoOtaku(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getInt("stock"));                                                                                                                                                                               
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        }
        return null;
    }
    
    // Crear un método para mostrar todos los productos de nuestra base de datos en formato lista
    public List<ProductoOtaku> obtenerTodosLosProductos() {
        List<ProductoOtaku> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        Connection conexion = databaseConnection.getConexion();
        try (
        	
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                productos.add(new ProductoOtaku(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getInt("stock")));                                                                                                                                                                                             
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
    
    // Crear un método para actualizar productos de la base de datos según su ID
    public boolean actualizarProducto(ProductoOtaku producto) {
        String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
        Connection conexion = databaseConnection.getConexion();
        try (
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
        	preparedStatement.setInt(1, producto.mostrarID());
            preparedStatement.setString(2, producto.mostrarNombre());
            preparedStatement.setString(3, producto.mostrarCategoria());
            preparedStatement.setDouble(4, producto.mostrarPrecio());
            preparedStatement.setInt(5, producto.mostrarStock());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
        return false;
    }
    
    // Crear un método para eliminar un producto de la base de datos en función de su ID
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        Connection conexion = databaseConnection.getConexion();
        try (
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
        return false;
    }
    
    // Crear un método que permita buscar productos por su nombre de nuestra base de datos y mostrarlos en forma de lista
    public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
        List<ProductoOtaku> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
        Connection conexion = databaseConnection.getConexion();
        try (
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + nombre + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productos.add(new ProductoOtaku(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getInt("stock")));                                                                                                                            
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar productos por nombre: " + e.getMessage());
        }
        return productos;
    }
    
    // Crear un método para buscar productos que compartan categoría de la base de datos según su categoría y mostrarlos en una lista
    public List<ProductoOtaku> buscarProductoPorCategoria(String categoria) {
        List<ProductoOtaku> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE categoria = ?";
        Connection conexion = databaseConnection.getConexion();
        try (
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, categoria);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productos.add(new ProductoOtaku(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getInt("stock")));                                                                                                                                                                                                            
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar productos por categoría: " + e.getMessage());
        }
        return productos;
    }
}