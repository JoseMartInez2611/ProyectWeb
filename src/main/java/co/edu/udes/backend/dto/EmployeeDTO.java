package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import co.edu.udes.backend.models.Borrow;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends ProfileUDTO {

    String workSpace;

}
