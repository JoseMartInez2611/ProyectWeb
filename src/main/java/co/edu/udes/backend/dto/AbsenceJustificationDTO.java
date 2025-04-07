package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AbsenceJustificationDTO {

    private long id;
    private String motive;
    private String description;
    private boolean justified;
}
