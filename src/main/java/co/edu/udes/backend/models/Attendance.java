package co.edu.udes.backend.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @OneToOne
    @JoinColumn(name = "id_justification", columnDefinition = "BIGINT")
    private AbsenceJustification justification;

}
