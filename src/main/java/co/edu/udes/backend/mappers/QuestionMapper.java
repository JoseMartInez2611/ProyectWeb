package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.models.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ExamMapper.class})
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    Question toEntity(QuestionDTO question);
    List<Question> toEntityList(List<QuestionDTO> questions);

    QuestionDTO toDto(Question question);
    List<QuestionDTO> toDtoList(List<Question> questions);
}
