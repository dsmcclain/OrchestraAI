package dev.dylan.BrassSection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Entity
@Table(name = "GENERATIONS")
@NoArgsConstructor
public class Generation {

    public Generation(String uuid, String promptUuid, String conversationUuid, String body) {
        this.uuid = uuid;
        this.promptUuid = promptUuid;
        this.conversationUuid = conversationUuid;
        this.body = body;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private String promptUuid;

    @JdbcTypeCode(SqlTypes.JSON)
    private String body;

    private String conversationUuid;

    @Override
    public String toString() {
        return "Generation{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", promptUuid='" + promptUuid + '\'' +
                ", response='" + body + '\'' +
                ", conversationUuid='" + conversationUuid + '\'' +
                '}';
    }
}
