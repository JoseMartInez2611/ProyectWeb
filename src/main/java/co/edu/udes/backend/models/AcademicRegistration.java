package co.edu.udes.backend.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AcademicRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @Column (name = "registration_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate registrationDate;
}
