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
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EvaluationDTO {

    long id;
    String evaluationRubric;
    Date date;
    GroupDTO group;

}
