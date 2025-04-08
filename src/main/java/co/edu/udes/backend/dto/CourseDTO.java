package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private long id;
    private String name;
    private int credits;
    private int theoreticalHours;
    private int practicalHours;
    private List<CourseDTO> prerequisites;
    private List<String> objectives;
    private List<String> content;
    private List<String> competences;
}
