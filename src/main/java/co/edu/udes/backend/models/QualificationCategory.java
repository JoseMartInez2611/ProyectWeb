package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qualification_categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QualificationCategory {

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
    private int percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_subperiod_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private AcademicSubperiod academicSubperiod;
}
