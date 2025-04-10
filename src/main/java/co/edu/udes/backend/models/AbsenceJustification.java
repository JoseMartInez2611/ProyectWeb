package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "absence_justifications")
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceJustification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motive",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String motive;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String description;

    @Column(name = "justified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean justified;
}
