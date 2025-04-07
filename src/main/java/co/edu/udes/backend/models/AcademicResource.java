package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_resource")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class AcademicResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "category", nullable = false, columnDefinition = "varchar(255)")
    private String category;

    @Column(name = "availability", nullable = false, columnDefinition = "boolean")
    private boolean availability;

}
