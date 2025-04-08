package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Evaluation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

public class Exam extends Evaluation {

    @OneToMany(
            targetEntity = Question.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "exam"
    )
    List<Question> questions;

}
