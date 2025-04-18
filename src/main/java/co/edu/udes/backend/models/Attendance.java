package co.edu.udes.backend.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_lesson",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "id_student",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Student student;

    @Column(name = "assistance",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean assistance;

    @Column(name = "date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate date;

    @OneToOne(mappedBy = "attendance")
    private AbsenceJustification justification;

}
