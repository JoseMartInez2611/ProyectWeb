package co.edu.udes.backend.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDTO {

    private long id;
    private CourseDTO course;
    private TeacherDTO teacher;
    private String academicPeriod;
}
