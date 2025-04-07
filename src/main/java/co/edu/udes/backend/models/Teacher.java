package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

public class Teacher extends User {

    @Column(name = "speciality", columnDefinition = "VARCHAR(255)", nullable = false)
    String speciality;

    @OneToMany(
            targetEntity = Group.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "teacher"
    )
    List<Group> groups;

}
