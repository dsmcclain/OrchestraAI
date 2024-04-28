package dev.dylan.BrassSection;

import dev.dylan.BrassSection.model.Prompt;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPromptRepository  extends BrassSectionApplicationTests{
    @Test
    void testFindAll() {
        List<Prompt> prompts = promptRepository.findAll();
        assertEquals(2, prompts.size());
    }

    @Test
    void testFindByUuid() {
        Prompt prompt = promptRepository.findByUuid("286d3946-8fcb-46e5-9a3d-5736727fd2f6").get();
        assertEquals("test body", prompt.getBody());
    }

    @Test
    void testFindByState() {
        List<Prompt> promptsCreated = promptRepository.findByState("CREATED");
        List<Prompt> promptsSent = promptRepository.findByState("SENT");

        assertAll(
                () -> assertEquals(2, promptsCreated.size()),
                () -> assertEquals(0, promptsSent.size())
        );
    }

    @Test
    void testFindAllBySentAtDesc() {
        List<Prompt> promptsDesc = promptRepository.findAllByOrderBySentAtDesc();
        assertEquals(promptsDesc.getFirst().getBody(), "most recent prompt");
    }

    @Test
    void testFindByStateOrderBySentAtDesc() {
        List<Prompt> unsentPrompts = promptRepository.findByStateOrderBySentAtDesc("CREATED");
        assertAll(
                () -> assertEquals(2, unsentPrompts.size()),
                () -> assertEquals("most recent prompt", unsentPrompts.getFirst().getBody())
        );
    }
}
