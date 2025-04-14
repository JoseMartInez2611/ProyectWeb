package co.edu.udes.backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResourceDTO {
    private long id;
    private String name;
    private String category;
    private boolean availability;
    
}
