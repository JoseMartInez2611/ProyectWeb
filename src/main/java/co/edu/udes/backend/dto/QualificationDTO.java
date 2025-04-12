package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class QualificationDTO {

    long id;
    StudentDTO student;
    float qualification;
    EvaluationDTO evaluation;

}
