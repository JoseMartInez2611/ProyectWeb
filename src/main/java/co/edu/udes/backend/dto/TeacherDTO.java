package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.UserDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TeacherDTO extends UserDTO {

    String speciality;
    List<GroupDTO> groups;

}
