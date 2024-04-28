package dev.dylan.Conductor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrompt {
    private String prompt;
    private Instant timestamp;
    private String uuid;
}
