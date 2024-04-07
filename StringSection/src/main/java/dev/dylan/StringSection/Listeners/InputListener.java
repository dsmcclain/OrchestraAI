package dev.dylan.StringSection.Listeners;

import dev.dylan.StringSection.models.Conversation;
import dev.dylan.StringSection.models.InteractionState;
import dev.dylan.StringSection.models.Prompt;
import dev.dylan.StringSection.services.GoogleConnector;
import dev.dylan.StringSection.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class InputListener {
    private GoogleConnector connector;
    private KafkaProducer producer;

    @Autowired
    public void promptListener(GoogleConnector googleConnector, KafkaProducer producer) {
        this.connector = googleConnector;
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
                .Uuid(promptUuid)
                .conversationUuid(conversationUuid)
                .body(in)
                .state(InteractionState.CREATED)
                .build();

        // TODO: this uses the PromptSerializer which is coupled to the google api.
        // we actually want a separate serializer for the data class itself.
        producer.sendMessage("prompts", prompt);
        return prompt;
    }
}
