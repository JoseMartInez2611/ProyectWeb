package co.edu.udes.backend.models.inheritance;


import co.edu.udes.backend.models.Group;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "evaluation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "evaluation_rubric")
    private String evaluationRubric;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private Group group;
}
