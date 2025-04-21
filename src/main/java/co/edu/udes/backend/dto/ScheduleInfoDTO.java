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
public class ScheduleInfoDTO {
    private String courseName;
    private String roomCode;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
}
