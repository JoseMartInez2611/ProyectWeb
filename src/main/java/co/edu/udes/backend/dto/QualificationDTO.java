package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QualificationDTO {

    long id;
    StudentDTO student;
    float qualification;
    EvaluationDTO evaluation;

}
