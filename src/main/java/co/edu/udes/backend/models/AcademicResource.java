package co.edu.udes.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "academic resource")
public class AcademicResource {

    private String name,description, category;
    private boolean availability;

}
