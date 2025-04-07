package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class ScheduleDTO {

    private long id;
    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeekDTO dayOfWeek;
}
