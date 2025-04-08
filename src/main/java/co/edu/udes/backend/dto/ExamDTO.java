package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ExamDTO extends EvaluationDTO {

    EvaluationDTO evaluation;
    List<QuestionDTO> question;
}
