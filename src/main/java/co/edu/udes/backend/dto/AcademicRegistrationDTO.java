package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRegistrationDTO {

    private long id;
    private Long studentId;
    public Long groupID;
    private LocalDate registrationDate;
}
