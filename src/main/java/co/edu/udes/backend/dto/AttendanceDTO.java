package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    private long id;
    private long lessonId;
    private long studentId;
    private boolean assistance;
    private LocalDate date;
    private long justificationId;
}
