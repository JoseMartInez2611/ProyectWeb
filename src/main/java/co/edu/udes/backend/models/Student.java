package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "student")

public class Student extends ProfileU {

    @Column(name = "semester", columnDefinition = "INTEGER", nullable = false)
    private int semester;

    @Column(name = "code", columnDefinition = "VARCHAR(255)", nullable = false)
    private String code;

    @Column(
            name = "date_birth",
            columnDefinition = "DATE",
            nullable = false)
    private LocalDate dateBirth;

    @Column(
            name = "address",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(
            name = "career_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Career career;

    @Column(
            name = "availableCredits",
            nullable = false,
            columnDefinition = "INT"
    )
    private int availableCredits;

    @ToString.Exclude
    @OneToMany(
            targetEntity = Attendance.class,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    private List<Attendance> attendance;

    @ToString.Exclude
    @OneToMany(
            targetEntity = AcademicRegistration.class,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    private List<AcademicRegistration> academicRegistration;

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
