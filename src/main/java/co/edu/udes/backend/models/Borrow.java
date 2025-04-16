package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrow_date", nullable = false, columnDefinition = "DATE")
    private LocalDate borrowDate;

    @Column(name = "return_date", nullable = false, columnDefinition = "DATE")
    private LocalDate returnDate;

    @Column(name = "duration", nullable = false, columnDefinition = "varchar(255)")
    private String duration;

    @ManyToOne (targetEntity = AcademicResource.class)
    @JoinColumn(name = "resource", nullable = false, columnDefinition = "BIGINT")
    private AcademicResource resource;

    @ManyToOne(targetEntity = ProfileU.class)
    private ProfileU petitioner;
}
