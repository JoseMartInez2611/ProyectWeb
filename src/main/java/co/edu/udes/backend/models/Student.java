package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@Builder
public class Student extends ProfileU {

    @Column(name = "semester", columnDefinition = "INTEGER", nullable = false)
    int semester;

    @Column(name = "code", columnDefinition = "VARCHAR(255)", nullable = false)
    String code;

    @Column(
            name = "date_birth",
            columnDefinition = "DATE",
            nullable = false)
    Date dateBirth;

    @Column(
            name = "address",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    String address;

    @Column(
            name = "career",
            columnDefinition = "VARCHAR(255)",
            nullable = false)
    String career;

    @OneToMany(
            targetEntity = Attendance.class,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    List<Attendance> attendance;

    @OneToMany(
            targetEntity = AcademicRegistration.class,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    List<AcademicRegistration> academicRegistration;


}
