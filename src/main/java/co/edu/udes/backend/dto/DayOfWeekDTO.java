package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayOfWeekDTO {
    private long id;
    private String day;
}
