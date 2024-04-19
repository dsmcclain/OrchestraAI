package dev.dylan.StringSection.models;

import lombok.*;

import java.time.Instant;

@Data
@Builder
public class Prompt {
    private String uuid;
    private String conversationUuid;
    private String body;
    private String generationUuid;
    private Instant sentAt;
    private InteractionState state;
}