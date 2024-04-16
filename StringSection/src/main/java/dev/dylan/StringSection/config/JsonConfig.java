package dev.dylan.StringSection.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dev.dylan.StringSection.models.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Instant;
import java.time.LocalDateTime;


@Configuration
public class JsonConfig {

    // Base Module for building specialized mappers
    public SimpleModule getBaseModule() {
        SimpleModule module = new SimpleModule();

        module.addSerializer(Instant.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        return module;
    }

    @Bean
    @Primary
    public ObjectMapper jsonMapper() {
        SimpleModule module = getBaseModule();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        return mapper;
    }

    public static class GeminiPromptMapper extends ObjectMapper {}
    @Bean
    public GeminiPromptMapper geminiPromptMapper() {
        GeminiPromptMapper mapper = new GeminiPromptMapper();
        SimpleModule module = getBaseModule();

        module.addSerializer(Prompt.class, new GeminiPromptSerializer(Prompt.class));

        mapper.registerModule(module);

        return mapper;
    }
}
