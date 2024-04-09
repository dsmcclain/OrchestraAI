package dev.dylan.BrassSection;

import dev.dylan.BrassSection.model.Generation;
import dev.dylan.BrassSection.repository.GenerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BrassSectionApplication {
	public static void main(String[] args) {
		SpringApplication.run(BrassSectionApplication.class, args);
	}
}
