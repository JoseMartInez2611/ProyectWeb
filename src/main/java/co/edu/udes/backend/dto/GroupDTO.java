package co.edu.udes.backend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    private long id;
    private CourseDTO course;
    private TeacherDTO teacher;
    private String academicPeriod;
}
