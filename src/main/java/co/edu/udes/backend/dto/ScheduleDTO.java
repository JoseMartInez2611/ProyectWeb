package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private long id;
    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeekDTO dayOfWeek;
}
