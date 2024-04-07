package dev.dylan.StringSection.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.StringSection.models.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GoogleConnector {
    private final WebClient client = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com")
            .build();
    private ObjectMapper jsonMapper;
    private String apiKey;
    private KafkaProducer kafkaProducer;


    @Autowired
    public void googleConnector(ObjectMapper jsonMapper, @Value("${gemini.key}") String apiKey, KafkaProducer kafkaProducer) {
        this.jsonMapper = jsonMapper;
        this.apiKey = apiKey;
        this.kafkaProducer = kafkaProducer;
    }

    public void send(Prompt prompt) throws JsonProcessingException {

        String body = jsonMapper.writeValueAsString(prompt);

        client.post()
                .uri("/v1beta/models/gemini-1.0-pro:generateContent?key={apiKey}", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> kafkaProducer.sendMessage("generations", response));
    }
}
