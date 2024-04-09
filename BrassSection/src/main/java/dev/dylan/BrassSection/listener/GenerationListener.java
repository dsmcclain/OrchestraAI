package dev.dylan.BrassSection.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.BrassSection.model.Generation;
import dev.dylan.BrassSection.repository.GenerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@Configuration
public class GenerationListener {
    private final GenerationRepository repository;

    @Autowired
    public GenerationListener(GenerationRepository repository) {
        this.repository = repository;
    }
    @KafkaListener(topics = "generations")
    public void consume(final String in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Generation record = mapper.readValue(in, Generation.class);
        repository.save(record);
    }
}
