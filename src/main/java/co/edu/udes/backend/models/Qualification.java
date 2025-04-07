package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Evaluation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "qualification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@Builder
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne(targetEntity = Student.class)
    @JoinColumn(name="student_id", nullable = false)
    Student student;

    @Column(name = "qualification", columnDefinition = "FLOAT", nullable = false)
    float qualification;

    @OneToOne(targetEntity = Evaluation.class)
    @JoinColumn(name = "evaluation_id", nullable = false)
    Evaluation evaluation;

}
