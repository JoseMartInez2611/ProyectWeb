package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
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
