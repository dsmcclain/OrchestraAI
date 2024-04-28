package dev.dylan.StringSection.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.dylan.StringSection.domain.Prompt;

import java.io.IOException;

public class GeminiPromptSerializer extends StdSerializer<Prompt> {
    public GeminiPromptSerializer() {
        this(null);
    }
    public GeminiPromptSerializer(Class<Prompt> t) {
        super(t);
    }

    @Override
    public void serialize(Prompt prompt, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        String body = prompt.getBody();
        gen.writeStartObject();
        gen.writeArrayFieldStart("contents");
        gen.writeStartObject();
        gen.writeArrayFieldStart("parts");
        gen.writeStartObject();
        gen.writeStringField("text", body);
        gen.writeEndObject();
        gen.writeEndArray();
        gen.writeEndObject();
        gen.writeEndArray();
        gen.writeEndObject();
    }

}
