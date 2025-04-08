package co.edu.udes.backend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

    private long id;
    private ScheduleDTO schedule;
    private RoomDTO classroom;
    private GroupDTO group;
}
