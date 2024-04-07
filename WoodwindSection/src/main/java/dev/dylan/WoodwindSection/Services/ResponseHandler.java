package dev.dylan.WoodwindSection.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandler {
    public String parse(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode goal = mapper.readTree(body).get("candidates").path(0).at("/content/parts").path(0).get("text");
        System.out.println(body);
        System.out.println(goal);

        return body;
    }
}
