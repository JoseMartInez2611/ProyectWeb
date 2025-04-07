package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ActivityDTO;

import co.edu.udes.backend.dto.AnswerDocumentDTO;
import co.edu.udes.backend.models.Activity;
import co.edu.udes.backend.models.AnswerDocument;

import java.util.Collections;
import java.util.List;

public class ActivityMapper {
    private final AnswerDocumentMapper answerDocumentMapper = new AnswerDocumentMapper();
    private final GroupMapper groupMapper = new GroupMapper();

    public  ActivityDTO toDTO(Activity activity) {

        return ActivityDTO.builder()
                //Datos Clase Padre
                .id(activity.getId())
                .evaluationRubric(activity.getEvaluationRubric())
                .date(activity.getDate())
                .group(groupMapper.toDTO(activity.getGroup()))

                //Datos Clase hija
                .description(activity.getDescription())
                .answerText(activity.getAnswerText())
                .answerDocument(answerDocumentMapper.toDTOList(activity.getAnswerDocuments()))
                .build();
    }

    public  Activity toEntity(ActivityDTO activityDTO) {
        return Activity.builder()
                //Datos Clase Padre
                .id(activityDTO.getId())
                .evaluationRubric(activityDTO.getEvaluationRubric())
                .date(activityDTO.getDate())
                .group(groupMapper.toEntity(activityDTO.getGroup()))

                //Datos Clase hija
                .description(activityDTO.getDescription())
                .answerText(activityDTO.getAnswerText())
                .answerDocuments(answerDocumentMapper.toEntityList(activityDTO.getAnswerDocument()))
                .build();
    }

    public List<Activity> toEntityList(List<ActivityDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ActivityDTO> toDTOList(List<Activity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

}
