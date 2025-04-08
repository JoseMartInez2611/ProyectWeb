package co.edu.udes.backend.mappers;


import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.models.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamMapper {
    private QuestionMapper questionMapper;
    private GroupMapper groupMapper;

    public ExamDTO toDTO(Exam exam) {
        return ExamDTO.builder()
                //Datos Clase Padre
                .id(exam.getId())
                .evaluationRubric(exam.getEvaluationRubric())
                .date(exam.getDate())
                .group(groupMapper.toDTO(exam.getGroup()))

                //Datos Clase Hija
                .question(questionMapper.toDTOList(exam.getQuestions()))
                .build();
    }

    public Exam toEntity(ExamDTO examDTO) {
        return Exam.builder()
                //Datos Clase Padre
                .id(examDTO.getId())
                .evaluationRubric(examDTO.getEvaluationRubric())
                .date(examDTO.getDate())
                .group(groupMapper.toEntity(examDTO.getGroup()))

                //Datos Clase Hija
                .questions(questionMapper.toEntityList(examDTO.getQuestion()))
                .build();
    }

    public List<Exam> toEntityList(List<ExamDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ExamDTO> toDTOList(List<Exam> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
