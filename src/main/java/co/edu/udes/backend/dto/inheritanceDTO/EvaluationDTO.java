package co.edu.udes.backend.dto.inheritanceDTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvaluationDTO {

    long id;
    String evaluationRubric;
    Date date;
    GroupDTO group;

}
