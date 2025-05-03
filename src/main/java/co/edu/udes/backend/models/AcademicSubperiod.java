package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "academic_subperiods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSubperiod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String name;

    @Column(name = "percentage",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int porcentage;
}
