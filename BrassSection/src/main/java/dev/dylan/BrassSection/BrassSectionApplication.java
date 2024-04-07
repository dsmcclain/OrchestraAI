package dev.dylan.BrassSection;

import dev.dylan.BrassSection.model.Generation;
import dev.dylan.BrassSection.repository.GenerationRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrassSectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrassSectionApplication.class, args);
	}

	@Bean
	public ApplicationRunner configure(GenerationRepository repository) {
		return env -> {
			String body = "\n" +
					"{\n" +
					"  \"candidates\": [\n" +
					"    {\n" +
					"      \"content\": {\n" +
					"        \"parts\": [\n" +
					"          {\n" +
					"            \"text\": \"Green leaves reach for sky\\nPhotosynthesis sustains life\\nNature's gift to us\"\n" +
					"          }\n" +
					"        ],\n" +
					"        \"role\": \"model\"\n" +
					"      },\n" +
					"      \"finishReason\": \"STOP\",\n" +
					"      \"index\": 0,\n" +
					"      \"safetyRatings\": [\n" +
					"        {\n" +
					"          \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n" +
					"          \"probability\": \"NEGLIGIBLE\"\n" +
					"        },\n" +
					"        {\n" +
					"          \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n" +
					"          \"probability\": \"NEGLIGIBLE\"\n" +
					"        },\n" +
					"        {\n" +
					"          \"category\": \"HARM_CATEGORY_HARASSMENT\",\n" +
					"          \"probability\": \"NEGLIGIBLE\"\n" +
					"        },\n" +
					"        {\n" +
					"          \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n" +
					"          \"probability\": \"NEGLIGIBLE\"\n" +
					"        }\n" +
					"      ]\n" +
					"    }\n" +
					"  ],\n" +
					"  \"promptFeedback\": {\n" +
					"    \"safetyRatings\": [\n" +
					"      {\n" +
					"        \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n" +
					"        \"probability\": \"NEGLIGIBLE\"\n" +
					"      },\n" +
					"      {\n" +
					"        \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n" +
					"        \"probability\": \"NEGLIGIBLE\"\n" +
					"      },\n" +
					"      {\n" +
					"        \"category\": \"HARM_CATEGORY_HARASSMENT\",\n" +
					"        \"probability\": \"NEGLIGIBLE\"\n" +
					"      },\n" +
					"      {\n" +
					"        \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n" +
					"        \"probability\": \"NEGLIGIBLE\"\n" +
					"      }\n" +
					"    ]\n" +
					"  }\n" +
					"}";
			Generation gen = new Generation("123", "456", "789", body);

			repository.save(gen);

			repository.findAll().forEach(System.out::println);
		};
	}
}
