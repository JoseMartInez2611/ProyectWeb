package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Lazy
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_period_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private AcademicPeriod academicPeriod;
}
