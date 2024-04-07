package dev.dylan.BrassSection.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@Configuration
public class PromptListener {
    @KafkaListener(topics = "prompts")
    public String listens(final String in) throws IOException {
        System.out.println(in);

        return in;
    }
}
