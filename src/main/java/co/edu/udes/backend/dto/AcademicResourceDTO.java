package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class AcademicResourceDTO {
    private Long id;
    private String name;
    private String category;
    private boolean availability;
}