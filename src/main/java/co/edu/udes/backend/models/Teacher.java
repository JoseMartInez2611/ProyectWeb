package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "teacher")
public class Teacher extends ProfileU {

    @Column(name = "speciality", columnDefinition = "VARCHAR(255)", nullable = false)
    private String speciality;

    @ToString.Exclude
    @OneToMany(
            targetEntity = Group.class,
            fetch = FetchType.LAZY,
            mappedBy = "teacher"
    )
    private List<Group> groups;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_career",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id")
    )
    private List<Career> careers;

}
