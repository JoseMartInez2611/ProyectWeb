package co.edu.udes.backend.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonDTO {

    private long id;
    private ScheduleDTO schedule;
    private RoomDTO classroom;
    private GroupDTO group;
}
