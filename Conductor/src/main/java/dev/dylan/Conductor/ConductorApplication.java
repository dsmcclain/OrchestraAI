package dev.dylan.Conductor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.Scanner;

@SpringBootApplication
public class ConductorApplication {
	// ObjectMapper is from Jackson, provides API for reading/writing java objects as JSON
	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(ConductorApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(final KafkaTemplate<String, String> kafkaTemplate) throws JsonProcessingException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a prompt:");
		String input;
		while(scanner.hasNextLine() && !((input = scanner.nextLine()).equals("exit"))) {
			final TestEvent event = TestEvent.builder()
					.prompt(input)
					.timestamp(LocalDateTime.now())
					.build();

			final String payload = objectMapper.writeValueAsString(event);

			kafkaTemplate.send("input", payload);
		}
		return args -> {};
	}

}
