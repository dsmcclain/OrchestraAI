package dev.dylan.BrassSection;

import dev.dylan.BrassSection.model.Prompt;
import dev.dylan.BrassSection.repository.PromptRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
// Create a single instance of the base test class and use it for all test methods
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BrassSectionApplicationTests {

	@Autowired
	PromptRepository promptRepository;

	@BeforeAll
	void beforeAll() {
		promptRepository.saveAll(generatePrompts());
	}
	private static List<Prompt> generatePrompts() {
		List<Prompt> prompts = new ArrayList<>();

		Prompt prompt1 = new Prompt(
				null,
				"286d3946-8fcb-46e5-9a3d-5736727fd2f6",
				UUID.randomUUID().toString(),
				"test body",
				UUID.randomUUID().toString(),
				LocalDateTime.now(),
				"CREATED"
		);

		Prompt prompt2 = new Prompt(
				null,
				"f7decbcc-3453-4710-9cbf-303c91e3de2a",
				UUID.randomUUID().toString(),
				"most recent prompt",
				UUID.randomUUID().toString(),
				LocalDateTime.now(),
				"SENT"
		);

		Prompt prompt3 = new Prompt(
				null,
				"dfabe83d-f3e5-4f86-848d-e9e18f5d295e",
				UUID.randomUUID().toString(),
				"most recent prompt",
				UUID.randomUUID().toString(),
				LocalDateTime.now(),
				"CREATED"
		);

		prompts.add(prompt1);
		prompts.add(prompt2);
		prompts.add(prompt3);

		return prompts;
	}

	@AfterAll
	void afterAll() {
		promptRepository.deleteAll();
	}
}
