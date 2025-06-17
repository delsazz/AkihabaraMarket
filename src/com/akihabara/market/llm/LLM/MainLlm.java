package com.akihabara.market.llm;
import java.util.Scanner;
public class MainLlm {
    public static void main(String[] args) {
    	
    	// Crear un nuevo objeto de tipo scanner
        Scanner scanner = new Scanner(System.in);
        
        // Crear un nuevo objeto de la clase LlmService
        LlmService llmService = new LlmService();
        
        // Introducir un prompt hasta que el usario introduzca la palabra "FIN"
        System.out.println("Introduce Un prompt para sugerir el nombre de un producto:");
        String prompt = scanner.nextLine();
        String nombreProductoSugerido = llmService.sugerirNombreProducto(prompt);
        
        // Mostrar la respuesta del LLM
        System.out.println("Respuesta del LLM:");
        System.out.println(nombreProductoSugerido);
        
        // Cerrar el scanner
        scanner.close();
    }
}