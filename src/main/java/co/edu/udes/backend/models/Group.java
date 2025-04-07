package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_course",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_teacher",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Teacher teacher;

    @Column(name = "academic_period",
            nullable = false,
            columnDefinition = "VARCHAR(5)"
    )
    private String academicPeriod;
}
