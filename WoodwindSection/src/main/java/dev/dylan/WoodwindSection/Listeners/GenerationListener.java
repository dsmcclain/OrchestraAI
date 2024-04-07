package dev.dylan.WoodwindSection.Listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.dylan.WoodwindSection.Services.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

// TODO: need to configure multiple concurrent listeners to same topic https://www.baeldung.com/spring-kafka-multiple-listeners-same-topic
@Configuration
public class GenerationListener {
    private ResponseHandler handler;
    @Autowired
    public void generationListener(ResponseHandler handler) {
        this.handler = handler;
    }
    @KafkaListener(topics = "generations")
    public String listens(final String in) throws JsonProcessingException {
        handler.parse(in);

        return in;
    }
    // What we need to do here is get the generation, and then use the promptId or conversationId to look up the prompt/response sequence in the db
    // then we can piece the whole convo together into something that looks like what we want to save in Notion, etc.
    // It's fine to have a db lookup here because we aren't under a significant time constraint. We could even look in to defining backpressure limits
    // for consuming the generations topic so that we don't overwhelm the datatabase with requests.
}
