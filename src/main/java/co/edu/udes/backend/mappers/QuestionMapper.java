package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.models.Question;

import java.util.Collections;
import java.util.List;

public class QuestionMapper {
    private final ExamMapper examMapper = new ExamMapper();

    public QuestionDTO toDTO(Question question){
        return QuestionDTO.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .questionType(question.getQuestionType())
                .answer(question.getAnswer())
                .exam(examMapper.toDTO(question.getExam()))
                .build();
    }

    public Question toEntity(QuestionDTO questionDTO){
        return Question.builder()
                .id(questionDTO.getId())
                .question(questionDTO.getQuestion())
                .questionType(questionDTO.getQuestionType())
                .answer(questionDTO.getAnswer())
                .exam(examMapper.toEntity(questionDTO.getExam()))
                .build();
    }

    public List<Question> toEntityList(List<QuestionDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<QuestionDTO> toDTOList(List<Question> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
