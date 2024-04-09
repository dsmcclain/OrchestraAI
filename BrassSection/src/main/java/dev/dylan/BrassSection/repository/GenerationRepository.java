package dev.dylan.BrassSection.repository;

import dev.dylan.BrassSection.model.Generation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends CrudRepository<Generation, Long> {
}
