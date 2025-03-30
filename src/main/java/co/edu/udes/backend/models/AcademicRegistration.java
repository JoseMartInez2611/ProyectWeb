package co.edu.udes.backend.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "academic_registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    public Group group;

    @Column (name = "registration_date")
    private LocalDate registrationDate;
}
