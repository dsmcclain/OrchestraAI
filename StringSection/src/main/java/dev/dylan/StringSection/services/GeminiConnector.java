package dev.dylan.StringSection.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.StringSection.config.JsonConfig.GeminiPromptMapper;
import dev.dylan.StringSection.models.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiConnector {
    private final WebClient client = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com")
            .build();
    private ObjectMapper geminiPromptMapper;
    private ResponseHandler responseHandler;
    private String apiKey;

    @Autowired
    public void geminiConnector(
            GeminiPromptMapper geminiPromptMapper,
            @Value("${gemini.key}") String apiKey,
            ResponseHandler responseHandler
    ) {
        this.geminiPromptMapper = geminiPromptMapper;
        this.apiKey = apiKey;
        this.responseHandler = responseHandler;
    }

    public void send(Prompt prompt) throws JsonProcessingException {

        String body = geminiPromptMapper.writeValueAsString(prompt);

        client.post()
                .uri("/v1beta/models/gemini-1.0-pro:generateContent?key={apiKey}", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> responseHandler.handleResponse(prompt, response));
    }
}
