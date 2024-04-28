package dev.dylan.StringSection.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJsonConfig {
    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private JsonConfig.GeminiPromptMapper geminiPromptMapper;

    @Test
    public void TestGetsCorrectMapper() {
        System.out.println(jsonMapper.getClass().getName());

        assertEquals(jsonMapper.getClass().getName(), "com.fasterxml.jackson.databind.ObjectMapper");
        assert(geminiPromptMapper.getClass().getName().contains("GeminiPromptMapper"));
    }

    @Test
    public void TestMappersContainBaseMapper() throws JsonProcessingException {
        String instantExpected = "2024-04-16T14:18:14.9688755Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        Instant t = Instant.now(clock);
        String serializedTime = jsonMapper.writeValueAsString(t);

        assertEquals(serializedTime, "\"2024-04-16T14:18:14.968875500\"");
    }


}
