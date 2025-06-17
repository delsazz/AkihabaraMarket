package com.akihabara.market.dao;
import com.akihabara.market.model.ProductoOtaku;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        
        // Crear un nuevo objeto de la clase DatabaseConnection para conectarse a la base de datos
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ProductoDAO productoDAO = new ProductoDAO(databaseConnection);
        
        // Crear un nuevo objeto con valores reales para la clase ProductoOtaku
        ProductoOtaku productoOtaku = new ProductoOtaku(18, "Sudadera Naruto", "Ropa", 30.0, 25);
        
        // Agregar el objeto creado de la clase ProductoOtaku
        productoDAO.agregarProducto(productoOtaku);
        
        // Obtener un producto por ID y mostrar su nombre
        ProductoOtaku productoOtaku1 = productoDAO.obtenerProductoPorId(1);
        if (productoOtaku1 != null) {
            System.out.println("Producto encontrado: " + productoOtaku1.mostrarNombre());
        } else {
            System.out.println("No se encontró el producto");
        }
        
        // Mostrar todos los productos añadidos a la base de datos en forma de lista
        List<ProductoOtaku> productos = productoDAO.obtenerTodosLosProductos();
        if (productos != null) {
            System.out.println("Productos:");
            for (ProductoOtaku producto : productos) {
                System.out.println("ID: " + producto.mostrarID()  + " Nombre: " + producto.mostrarNombre() + " Categoría: " + producto.mostrarCategoria() + " Precio: " + producto.mostrarPrecio() + " Stock: " + producto.mostrarStock());
            }
        } else {
            System.out.println("No se han encontrado productos");
        }

        // Cerrar la conexión con la base de datos
        databaseConnection.cerrarConexion();
    }
}