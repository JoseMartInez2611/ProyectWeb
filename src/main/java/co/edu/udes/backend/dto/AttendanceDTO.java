package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

    private long id;
    private LessonDTO lesson;
    private StudentDTO student;
    private boolean assistance;
    private LocalDate date;
    private AbsenceJustificationDTO justification;
}
