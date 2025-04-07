package co.edu.udes.backend.models.inheritance;


import co.edu.udes.backend.models.Group;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "evaluation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@SuperBuilder


public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "evaluation_rubric", columnDefinition = "VARCHAR(1024)", nullable = false)
    String evaluationRubric;

    @Column(name="date", columnDefinition = "DATE", nullable = false)
    Date date;

    @ManyToOne(targetEntity = Group.class)
    Group group;

}
