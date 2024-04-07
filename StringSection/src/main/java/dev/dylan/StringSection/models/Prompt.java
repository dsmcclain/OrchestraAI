package dev.dylan.StringSection.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Prompt {
    private String Uuid;
    private String conversationUuid;
    private String body;
    private String generationUuid;
    private LocalDateTime sentAt;
    private InteractionState state;
}