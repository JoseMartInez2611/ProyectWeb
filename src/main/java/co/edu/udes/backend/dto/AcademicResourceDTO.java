package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicResourceDTO {
    private long id;
    private String name;
    private String description;
    private String category;
    private boolean availability;
    private long roomId;
}