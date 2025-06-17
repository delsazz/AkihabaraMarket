package com.akihabara.market.llm;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class LlmService {
	
	// Introducir la ApiKey y protegerla
    private String apiKey = "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";
    
    // Crear un método para introducir nombres de producto según el prompt introducido
    public String sugerirNombreProducto(String prompt) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            JsonObject message = new JsonObject();
            message.addProperty("role", "user");
            message.addProperty("content", prompt);
            JsonArray messages = new JsonArray();
            messages.add(message);
            JsonObject body = new JsonObject();
            body.addProperty("model", "mistralai/mistral-7b-instruct:free");
            body.add("messages", messages);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            return json.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();
        } catch (Exception e) {
            System.out.println("Error al generar la respuesta o comunicarse con OpenRouter: " + e.getMessage());
        }
		return null;
    }
}
