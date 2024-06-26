package dev.dylan.BrassSection.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Entity
@Table(name = "GENERATIONS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Generation {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private String promptUuid;
    private String conversationUuid;

    @Column(columnDefinition = "TEXT")
    private String body;

    @JdbcTypeCode(SqlTypes.JSON)
    private String rawResponse;
}
