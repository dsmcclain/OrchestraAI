package dev.dylan.BrassSection.repository;

import dev.dylan.BrassSection.model.Prompt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptRepository extends CrudRepository<Prompt, Long> {
}
