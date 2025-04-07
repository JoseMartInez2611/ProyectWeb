package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "borrow_date", nullable = false, columnDefinition = "DATE")
    private LocalDate borrowDate;

    @Column(name = "return_date", nullable = false, columnDefinition = "DATE")
    private LocalDate returnDate;

    @Column(name = "duration", nullable = false, columnDefinition = "varchar(255)")
    private String duration;

    @ManyToOne (targetEntity = AcademicResource.class)
    @JoinColumn(name = "resource", nullable = false, columnDefinition = "varchar(255)")
    private AcademicResource resource;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "lender", nullable = false, columnDefinition = "varchar(255)")
    private Employee lender;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "petitioner", nullable = false, columnDefinition = "varchar(255)")
    private User petitioner;
}
