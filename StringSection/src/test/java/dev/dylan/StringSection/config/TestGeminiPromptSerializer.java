package dev.dylan.StringSection.config;

import dev.dylan.StringSection.domain.InteractionState;
import dev.dylan.StringSection.domain.Prompt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestGeminiPromptSerializer {

    @Autowired
    JsonConfig.GeminiPromptMapper geminiPromptMapper;

    private final Prompt prompt = Prompt.builder()
            .uuid(UUID.randomUUID().toString())
            .conversationUuid(UUID.randomUUID().toString())
            .body("Test Prompt")
            .state(InteractionState.CREATED)
            .build();
    @Test
    public void testGeminiPromptMapper() throws IOException {
        String serializedPrompt = geminiPromptMapper.writeValueAsString(prompt);

        assertEquals("{\"contents\":[{\"parts\":[{\"text\":\"Test Prompt\"}]}]}", serializedPrompt);
    }

    @Test
    void testGeminiPromptSerializer() throws IOException {
        GeminiPromptSerializer serializer = new GeminiPromptSerializer(Prompt.class);
        // boilerplate for testing Jackson serializers
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();

        String body = """
               {"contents":[{"parts":[{"text":"Test Prompt"}]}]}""";

        serializer.serialize(prompt, jsonGenerator, serializerProvider);
        jsonGenerator.flush();

        assertEquals(jsonWriter.toString(), body);
    }
}
