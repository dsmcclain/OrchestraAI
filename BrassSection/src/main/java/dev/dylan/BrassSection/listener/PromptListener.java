package dev.dylan.BrassSection.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.BrassSection.model.Prompt;
import dev.dylan.BrassSection.repository.PromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@Configuration
public class PromptListener {
    private final PromptRepository repository;

    @Autowired
    public PromptListener(PromptRepository repository) {
        this.repository = repository;
    }
    @KafkaListener(topics = "prompts")
    public void consume(final String in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Prompt record = mapper.readValue(in, Prompt.class);
        repository.save(record);
    }
}