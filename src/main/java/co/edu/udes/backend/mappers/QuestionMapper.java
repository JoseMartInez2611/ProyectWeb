package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.models.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ExamMapper.class})
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(source = "examId", target = "exam.id")
    Question toEntity(QuestionDTO question);
    List<Question> toEntityList(List<QuestionDTO> questions);

    @Mapping(source = "exam.id", target = "examId")
    QuestionDTO toDto(Question question);
    List<QuestionDTO> toDtoList(List<Question> questions);
}
