package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "academic_periods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "academic_year",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int academicYear;

    @Column(name = "period",
            nullable = false,
            columnDefinition = "CHAR(1)"
    )
    private char period;

    public String getAcademicPeriod() {
        return String.format("%d%c", academicYear, period);
    }
}
