package dev.dylan.StringSection.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {
    private String Uuid;
    private String title;
    private LocalDateTime createdAt;
    private InteractionState state;
    private String originalPromptUuid;
}
