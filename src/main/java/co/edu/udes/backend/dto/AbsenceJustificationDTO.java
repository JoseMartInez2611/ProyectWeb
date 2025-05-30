package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceJustificationDTO {

    private long id;
    private String motive;
    private String description;
    private boolean justified;
    private long attendanceId;
}
