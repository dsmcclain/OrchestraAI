package dev.dylan.BrassSection.repository;

import dev.dylan.BrassSection.model.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {

    Optional<Prompt> findByUuid(String uuid);
    List<Prompt> findByState(String state);

    List<Prompt> findAllByOrderBySentAtDesc();

    List<Prompt> findByStateOrderBySentAtDesc(String state);

}
