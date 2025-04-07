package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class AttendanceDTO {

    private long id;
    private LessonDTO lesson;
    private StudentDTO student;
    private boolean assistance;
    private LocalDate date;
    private AbsenceJustificationDTO justification;
}
