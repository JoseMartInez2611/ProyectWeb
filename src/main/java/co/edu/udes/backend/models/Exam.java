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
@Table(name = "exam")

public class Exam extends Evaluation {

    @OneToMany(
            targetEntity = Question.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "exam"
    )
    private List<Question> questions;

}
