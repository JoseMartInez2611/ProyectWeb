package co.edu.udes.backend.models.inheritance;


import co.edu.udes.backend.models.Group;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "evaluation")

public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(name = "evaluation_rubric", columnDefinition = "VARCHAR(1024)", nullable = false)
    private String evaluationRubric;

    @Column(name="date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne(targetEntity = Group.class)
    private Group group;

}
