package co.edu.udes.backend.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_registrations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  AcademicRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    public Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_period_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private AcademicPeriod academicPeriod;
}
