package dev.dylan.StringSection.services;

import dev.dylan.StringSection.models.Generation;
import dev.dylan.StringSection.models.Prompt;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResponseHandler {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public ResponseHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    public void handleResponse(Prompt prompt, String response) {
        Generation generation = new Generation(UUID.randomUUID().toString(),  prompt.getUuid(), prompt.getConversationUuid(), response);
        kafkaProducer.sendMessage("generations",generation);
    }
}
