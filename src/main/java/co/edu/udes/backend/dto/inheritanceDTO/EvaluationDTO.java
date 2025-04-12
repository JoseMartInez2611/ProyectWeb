package co.edu.udes.backend.dto.inheritanceDTO;

import co.edu.udes.backend.dto.GroupDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDTO {

    long id;
    String evaluationRubric;
    Date date;
    GroupDTO group;

}
