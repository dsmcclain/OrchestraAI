package dev.dylan.StringSection.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// https://stackoverflow.com/questions/41749539/how-to-serialize-localdatetime-with-jackson
public class LocalDateTimeSerializer extends JsonSerializer<Instant> {
    private final DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.atZone(ZoneId.of("UTC")).toString().split("Z")[0]);
    }
}
