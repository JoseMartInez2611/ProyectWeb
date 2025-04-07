package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.UserDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO extends UserDTO {S
    int semester;
    String code;
    Date dateBirth;
    String address;
    String career;
    AttendanceDTO attendance;
    AcademicRegistrationDTO academicRegistration;

}
