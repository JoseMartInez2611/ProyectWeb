package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Evaluation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

public class Activity extends Evaluation {

    @Column(name = "description", columnDefinition = "VARCHAR(1024)", nullable = false)
    String description;

    @Column(name = "answer_text", columnDefinition = "VARCHAR(1024)", nullable = false)
    String answerText;

    @OneToMany(
            targetEntity = AnswerDocument.class,
            fetch = FetchType.LAZY,
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<AnswerDocument> answerDocuments;
}
