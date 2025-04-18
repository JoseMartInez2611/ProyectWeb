package co.edu.udes.backend.models.inheritance;


import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.Qualification;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "evaluation_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String evaluationName;

    @Column(name = "evaluation_rubric", columnDefinition = "VARCHAR(1024)", nullable = false)
    private String evaluationRubric;

    @Column(name="date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne(targetEntity = Group.class)
    private Group group;


    @OneToMany(
            targetEntity = Qualification.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "evaluation"
    )
    private List<Qualification> qualification;


}
