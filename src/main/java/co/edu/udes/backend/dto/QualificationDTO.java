package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QualificationDTO {

    long id;
    StudentDTO student;
    float qualification;
    EvaluationDTO evaluation;

}
