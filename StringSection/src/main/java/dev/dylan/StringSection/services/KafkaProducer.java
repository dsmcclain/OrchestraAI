package dev.dylan.StringSection.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
   private final KafkaTemplate<String, String> kafkaTemplate;
   private final ObjectMapper jsonMapper;

   @Autowired
   public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper jsonMapper) {
       this.kafkaTemplate = kafkaTemplate;
       this.jsonMapper = jsonMapper;
   }

   public void sendMessage(String topicName, String message) {
       kafkaTemplate.send(topicName, message);
   }

   public void sendMessage(String topicName, Object object) {
       try {
           String serializedObject = jsonMapper.writeValueAsString(object);
           kafkaTemplate.send(topicName, serializedObject);
       } catch (JsonProcessingException e) {
           System.out.println("Unable to publish object: " + object.toString());
           e.printStackTrace();
    }
   }
}
