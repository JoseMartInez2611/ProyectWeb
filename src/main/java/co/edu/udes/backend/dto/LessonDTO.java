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
public class LessonDTO {

    private long id;
    private long scheduleId;
    private long classroomId;
    private long groupId;
}
