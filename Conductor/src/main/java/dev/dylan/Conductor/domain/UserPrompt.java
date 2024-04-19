package dev.dylan.Conductor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrompt {
    private String prompt;
    private LocalDateTime timestamp; // todo: convert to Instant and add serializer
    private String uuid;
}
