package dev.dylan.WoodwindSection.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandler {
    public String parse(String body) throws JsonProcessingException {
        System.out.println(body);

        return body;
    }
}
