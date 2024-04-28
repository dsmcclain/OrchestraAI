package dev.dylan.StringSection.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Generation {
    private String uuid;
    private String promptUuid;
    private String conversationUuid;
    private String body;
    private String rawResponse;
}
