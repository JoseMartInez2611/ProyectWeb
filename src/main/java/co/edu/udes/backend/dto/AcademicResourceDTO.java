package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResourceDTO {
    private Long id;
    private String name;
    private String category;
    private boolean availability;
}