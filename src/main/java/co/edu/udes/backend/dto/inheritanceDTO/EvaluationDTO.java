package co.edu.udes.backend.dto.inheritanceDTO;

import co.edu.udes.backend.dto.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EvaluationDTO {

    private long id;
    private String evaluationRubric;
    private Date date;
    private GroupDTO group;

}
