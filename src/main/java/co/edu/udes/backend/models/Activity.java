package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Evaluation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "activity")

public class Activity extends Evaluation {

    @Column(name = "description", columnDefinition = "VARCHAR(1024)", nullable = false)
    private String description;

    @Column(name = "answer_text", columnDefinition = "VARCHAR(1024)", nullable = false)
    private String answerText;

    @OneToMany(
            targetEntity = AnswerDocument.class,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "activity"
    )
    private List<AnswerDocument> answerDocuments;
}
