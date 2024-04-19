package dev.dylan.Conductor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.Conductor.domain.UserPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PromptService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public PromptService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    public String generateNewPrompt(String input) {
        String uuid = UUID.randomUUID().toString();
        final UserPrompt userPrompt = UserPrompt.builder()
        .prompt(input)
        .timestamp(LocalDateTime.now())
        .uuid(uuid)
        .build();

        sendMessage(userPrompt);
        return uuid;
    }

    public void sendMessage(UserPrompt prompt){
        String payload;
        try {
            payload = objectMapper.writeValueAsString(prompt);
        } catch (JsonProcessingException e) {
            System.out.println("Error: unable to serialize object: " + prompt.toString());
            return;
        }

        kafkaTemplate.send("input", payload);
    }

    public String getPrompt(String uuid) {
        return "implement me by calling out to BrassSection";
    }
}
