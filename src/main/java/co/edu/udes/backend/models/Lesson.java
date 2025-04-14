package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_schedule",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "id_classroom",
            nullable = false,
            columnDefinition = "BIGINT"
            )
    private Room classroom;

    @ManyToOne
    @JoinColumn(name = "id_group",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Group group;
}
