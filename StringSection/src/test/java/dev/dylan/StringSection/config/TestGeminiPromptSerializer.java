package dev.dylan.StringSection.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.dylan.StringSection.domain.Prompt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestGeminiPromptSerializer {

    @Autowired
    JsonConfig.GeminiPromptMapper geminiPromptMapper;

    @Test
    public void testGeminiPromptSerialization() throws JsonProcessingException {
        Prompt prompt = Prompt.builder().uuid(UUID.randomUUID().toString())
                .body("Test Prompt")
                .build();

        String serializedPrompt = geminiPromptMapper.writeValueAsString(prompt);

        assertEquals("{\"contents\":[{\"parts\":[{\"text\":\"Test Prompt\"}]}]}", serializedPrompt);
    }
}
