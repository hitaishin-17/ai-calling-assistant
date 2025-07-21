package com.hitaishi.aicall.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Client to interact with the Groq LLM API for generating AI call scripts.
 */
@Component
public class GroqApiClient {

    private final WebClient webClient;

    @Value("${groq.api_key}")
    private String apiKey;

    public GroqApiClient(@Value("${groq.api_key}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    /**
     * Sends a prompt to Groq and returns the generated response content.
     *
     * @param prompt input purpose or message for AI
     * @return generated call script text
     */
    public String generateCallScript(String prompt) {
        String body = """
{
  "model": "llama3-70b-8192",
  "messages": [
    {
      "role": "system",
      "content": "You are an intelligent and friendly voice assistant working for an EdTech company. \
Speak in a natural, human-like tone. Maintain a professional and helpful attitude. \
Your goal is to assist users with inquiries about online courses, pricing, and appointments for counseling sessions. \
Avoid repeating greetings or questions if the user has already responded. \
Respond based on the user's last message and move the conversation forward like a human agent would. \
Keep responses short, clear, and polite. If the user expresses interest, guide them to the next step, such as booking an appointment or explaining a course. \
Do not ask 'how are you' or restart the conversation unless absolutely necessary. \
Do not use Hindi or informal phrases like 'beta' or 'arre'. Stay friendly, but professional."
    },
    {
      "role": "user",
      "content": "%s"
    }
  ],
  "temperature": 0.7
}
""".formatted(prompt.replace("\"", "\\\""));

        try {
            return webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(response -> {
                        System.out.println("Groq raw response: " + response);
                        return extractContent(response);
                    })
                    .block();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Sorry, we couldn't generate your call script.";
        }
    }

    /**
     * Extracts the 'content' field from the Groq response JSON.
     */
    private String extractContent(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to parse response from Groq.";
        }
    }

    @PostConstruct
    public void testKey() {
        System.out.println("API Key: " + apiKey); // Should start with gsk_
    }
}