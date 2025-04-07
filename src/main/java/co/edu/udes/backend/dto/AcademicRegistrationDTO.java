package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class AcademicRegistrationDTO {

    private long id;
    private StudentDTO student;
    public GroupDTO group;
    private LocalDate registrationDate;
}
