package co.edu.udes.backend.models;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_records")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "academic_average",
            nullable = false,
            columnDefinition = "REAL DEFAULT 0.0"
    )
    private float academicAverage;

    @OneToOne
    @JoinColumn(name = "student_id",
            unique = true,
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Student student;

    @OneToMany(mappedBy = "academicRecord", fetch = FetchType.LAZY)
    private List<FinalNote> finalNotes;
}
