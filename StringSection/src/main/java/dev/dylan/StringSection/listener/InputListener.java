package dev.dylan.StringSection.listener;

import dev.dylan.StringSection.domain.Conversation;
import dev.dylan.StringSection.domain.InteractionState;
import dev.dylan.StringSection.domain.Prompt;
import dev.dylan.StringSection.service.GeminiConnector;
import dev.dylan.StringSection.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
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
        String promptUuid = UUID.randomUUID().toString();
        String conversationUuid = UUID.randomUUID().toString();
        Conversation conversation = buildConversation(conversationUuid, promptUuid);
        Prompt prompt = buildPrompt(promptUuid, conversationUuid, in);

        connector.send(prompt);

        return in;
    }

    Conversation buildConversation(String conversationUuid, String promptUuid) {
        Conversation conversation =  Conversation.builder()
                .Uuid(conversationUuid)
                .createdAt(LocalDateTime.now())
                .state(InteractionState.CREATED)
                .originalPromptUuid(promptUuid)
                .build();

        producer.sendMessage("conversations", conversation);
        return conversation;
    }

    Prompt buildPrompt(String promptUuid, String conversationUuid, String in) {
        Prompt prompt = Prompt.builder()
                .uuid(promptUuid)
                .conversationUuid(conversationUuid)
                .body(in)
                .state(InteractionState.CREATED)
                .build();

        producer.sendMessage("prompts", prompt);
        return prompt;
    }
}
