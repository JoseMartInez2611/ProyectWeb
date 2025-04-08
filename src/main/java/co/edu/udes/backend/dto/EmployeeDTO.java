package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO extends ProfileUDTO {

    String workSpace;

}
