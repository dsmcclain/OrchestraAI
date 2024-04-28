package dev.dylan.StringSection.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Prompt {
    private String uuid;
    private String conversationUuid;
    private String body;
    private String generationUuid;
    private LocalDateTime sentAt;
    private InteractionState state;
}