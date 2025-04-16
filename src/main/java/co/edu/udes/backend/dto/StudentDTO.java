package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class StudentDTO extends ProfileUDTO {
    private int semester;
    private String code;
    private LocalDate dateBirth;
    private String address;
    private String career;
    private List<AttendanceDTO> attendance;
    private List<AcademicRegistrationDTO> academicRegistration;
    private List<QualificationDTO> qualification;

}
