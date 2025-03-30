package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Evaluation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "qualification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="student_id", nullable = false)
    private Student student;

    @Column(name = "qualification")
    private float qualification;

    @OneToOne
    @JoinColumn(name = "evaluation", nullable = false)
    private Evaluation evaluation;

}
