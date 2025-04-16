package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Evaluation;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "qualification")

public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(
            name="id_student",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Student student;

    @Column(name = "qualification", columnDefinition = "FLOAT", nullable = false)
    private float qualification;

    @OneToOne(targetEntity = Evaluation.class)
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;

}
