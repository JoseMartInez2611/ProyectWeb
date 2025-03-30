package co.edu.udes.backend.models;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AcademicResource {

    private String name,description, category;
    private boolean availability;

}
