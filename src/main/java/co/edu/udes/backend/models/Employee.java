package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

public class Employee extends ProfileU {

    @Column(name="work_space", columnDefinition = "VARCHAR(255)", nullable = false)
    String workSpace;

}
