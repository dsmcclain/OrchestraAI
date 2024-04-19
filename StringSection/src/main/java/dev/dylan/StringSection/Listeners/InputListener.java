package dev.dylan.StringSection.Listeners;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.StringSection.models.Conversation;
import dev.dylan.StringSection.models.InteractionState;
import dev.dylan.StringSection.models.Prompt;
import dev.dylan.StringSection.services.GeminiConnector;
import dev.dylan.StringSection.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class InputListener {
    private GeminiConnector connector;
    private KafkaProducer producer;

    @Autowired
    public void promptListener(GeminiConnector geminiConnector, KafkaProducer producer) {
        this.connector = geminiConnector;
        this.producer = producer;
    }

    @KafkaListener(topics = "input")
    public String listens(final String in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode input = mapper.readTree(in);
        String conversationUuid = UUID.randomUUID().toString();
        Prompt prompt = buildPrompt(conversationUuid, input);
        Conversation conversation = buildConversation(conversationUuid, prompt.getUuid());

        connector.send(prompt);

        return in;
    }

    Conversation buildConversation(String conversationUuid, String promptUuid) {
        Conversation conversation =  Conversation.builder()
                .uuid(conversationUuid)
                .createdAt(Instant.now())
                .state(InteractionState.CREATED)
                .originalPromptUuid(promptUuid)
                .build();

        producer.sendMessage("conversations", conversation);
        return conversation;
    }

    Prompt buildPrompt(String conversationUuid, JsonNode input) {
        Prompt prompt = Prompt.builder()
                .uuid(input.get("uuid").toString())
                .conversationUuid(conversationUuid)
                .body(input.get("prompt").toString())
                .state(InteractionState.CREATED)
                .build();

        producer.sendMessage("prompts", prompt);
        return prompt;
    }
}
