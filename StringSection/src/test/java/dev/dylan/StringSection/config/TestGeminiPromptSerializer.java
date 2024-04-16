package dev.dylan.StringSection.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.dylan.StringSection.models.InteractionState;
import dev.dylan.StringSection.models.Prompt;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Target;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGeminiPromptSerializer {
    private final Prompt prompt = Prompt.builder()
            .Uuid(UUID.randomUUID().toString())
                .conversationUuid(UUID.randomUUID().toString())
                .body("test prompt body")
                .state(InteractionState.CREATED)
                .build();

    private final GeminiPromptSerializer serializer = new GeminiPromptSerializer(Prompt.class);


    @Test
    void TestPromptSerialization() throws IOException {
        // boilerplate for testing Jackson serializers
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();

        String body = """
               {"contents":[{"parts":[{"text":"test prompt body"}]}]}""";

        serializer.serialize(prompt, jsonGenerator, serializerProvider);
        jsonGenerator.flush();

        assertEquals(jsonWriter.toString(), body);
    }
}
