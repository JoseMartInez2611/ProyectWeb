package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@ToString(includeFieldNames = false, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee")

public class Employee extends ProfileU {

    @Column(name="work_space", columnDefinition = "VARCHAR(255)", nullable = false)
    private String workSpace;

}
