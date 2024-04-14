package dev.dylan.BrassSection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "PROMPTS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prompt {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private String conversationUuid;
    private String body;
    private String generationUuid;
    private LocalDateTime sentAt;
    private String state;
}
