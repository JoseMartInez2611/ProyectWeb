package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends ProfileUDTO {
    int semester;
    String code;
    Date dateBirth;
    String address;
    String career;
    List<AttendanceDTO> attendance;
    List<AcademicRegistrationDTO> academicRegistration;

}
