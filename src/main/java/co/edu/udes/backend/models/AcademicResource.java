package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_resource")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AcademicResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "category", nullable = false, columnDefinition = "varchar(255)")
    private String category;

    @Column(name = "availability", nullable = false, columnDefinition = "boolean")
    private boolean availability;

    @ManyToOne
    @JoinColumn (name = "id_room", nullable = false, columnDefinition = "BIGINT")
    private Room room;

}
