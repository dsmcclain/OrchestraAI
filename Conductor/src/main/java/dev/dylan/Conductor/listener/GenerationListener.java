//package dev.dylan.Conductor.listener;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.KafkaListener;
//
//import java.io.IOException;
//
//// todo: get this listener working. or not. Maybe conductor has no listeners.
//@Configuration
//public class GenerationListener {
//
//@KafkaListener(topics = "generations")
//    public void consume(final String in) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String body = mapper.readTree(in).get("body").toString();
//        System.out.println(body);
//    }
//}
