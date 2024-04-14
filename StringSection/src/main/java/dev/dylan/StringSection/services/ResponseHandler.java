package dev.dylan.StringSection.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.dylan.StringSection.models.Generation;
import dev.dylan.StringSection.models.Prompt;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;

@Service
public class ResponseHandler {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public ResponseHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    public void handleResponse(Prompt prompt, String response) {
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        try {
            body = mapper.readTree(response).get("candidates").path(0).at("/content/parts").path(0).get("text").toString();    
        } catch (JsonProcessingException e){
            System.out.println(e);
            e.printStackTrace();
        }
        Generation generation = new Generation(UUID.randomUUID().toString(),  prompt.getUuid(), prompt.getConversationUuid(), body, response);
        kafkaProducer.sendMessage("generations",generation);
    }
}
