package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class QualificationDTO {

    private long id;
    private StudentDTO student;
    private float qualification;
    private EvaluationDTO evaluation;

}
