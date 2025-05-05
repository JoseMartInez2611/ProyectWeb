package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private long id;
    private String name;
    private int credits;
    private int theoreticalHours;
    private int practicalHours;
    private List<Long> prerequisiteIds;
    private List<String> objectives;
    private List<String> content;
    private List<String> competences;
    private long careerId;
    private List<Long> equivalencesIds;
    private int semester;
}
