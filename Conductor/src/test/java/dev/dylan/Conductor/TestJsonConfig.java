package dev.dylan.Conductor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dylan.Conductor.config.JsonConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@SpringBootTest(classes = ActiveProfiles.class)
@ActiveProfiles(value = "test")
public class TestJsonConfig {
    public ObjectMapper objectMapper = new JsonConfig().objectMapper();
    @Test
    void TestTimestampFormat() throws JsonProcessingException {
        String instantExpected = "2014-12-22T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        LocalDateTime t = LocalDateTime.now(clock);

        Assertions.assertEquals(objectMapper.writeValueAsString(t), "\"2014-12-22T10:15:30\"");
    }
}
