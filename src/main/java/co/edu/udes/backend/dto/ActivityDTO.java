package co.edu.udes.backend.dto;


import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class ActivityDTO extends EvaluationDTO {

    private String description;
    private String answerText;
    private List<AnswerDocumentDTO> answerDocument;

}
