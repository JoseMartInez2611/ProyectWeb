package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "final_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_academic_record",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private AcademicRecord academicRecord;

    @ManyToOne
    @JoinColumn(name = "id_group",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Group group;

    @Column(name = "note",
            nullable = false,
            columnDefinition = "REAL"
    )
    private float note;
}
