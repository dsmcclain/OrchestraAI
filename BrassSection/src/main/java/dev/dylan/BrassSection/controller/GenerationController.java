package dev.dylan.BrassSection.controller;

import dev.dylan.BrassSection.model.Generation;
import dev.dylan.BrassSection.repository.GenerationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerationController {

    private final GenerationRepository generationRepository;

    public GenerationController(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }
    @GetMapping("/generations")
    public Iterable<Generation> findAllGenerations() {
        return this.generationRepository.findAll();
    }

    @PostMapping("/generations")
    public Generation addOneGeneration(@RequestBody Generation generation) {
        return this.generationRepository.save(generation);
    }
}
