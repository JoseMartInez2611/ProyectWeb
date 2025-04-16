package co.edu.udes.backend.dto.inheritanceDTO;

import co.edu.udes.backend.models.Qualification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EvaluationDTO {

    private long id;
    private String evaluationName;
    private String evaluationRubric;
    private LocalDate date;
    private long groupId;
    private List<Qualification> qualification;

}
