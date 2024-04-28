package dev.dylan.Conductor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Profile("dev")
public class ConductorApplication {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	Environment env;
	public static void main(String[] args) {
		SpringApplication.run(ConductorApplication.class, args);
	}

//	@Bean
//	@Profile("!test")
//	public ApplicationRunner runner(final KafkaTemplate<String, String> kafkaTemplate) throws JsonProcessingException {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter a prompt:");
//		String input;
//		while (scanner.hasNextLine() && !((input = scanner.nextLine()).equals("exit"))) {
//			final UserPrompt userPrompt = UserPrompt.builder()
//					.prompt(input)
//					.timestamp(LocalDateTime.now())
//					.uuid(UUID.randomUUID().toString())
//					.build();
//
//			final String payload = objectMapper.writeValueAsString(userPrompt);
//
//			kafkaTemplate.send("input", payload);
//		}
//		return args -> {
//		};
//	}
}
