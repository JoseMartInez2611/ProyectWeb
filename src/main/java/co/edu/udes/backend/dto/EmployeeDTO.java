package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.UserDTO;
import co.edu.udes.backend.models.Borrow;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO extends UserDTO {

    String workSpace;

}
