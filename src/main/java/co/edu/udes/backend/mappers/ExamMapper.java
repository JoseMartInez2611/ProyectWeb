package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.models.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class, GroupMapper.class})
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    @Mapping(source = "qualificationCategoryId", target = "qualificationCategory.id")
    Exam toEntity(ExamDTO exam);
    List<Exam> toEntityList(List<ExamDTO> exams);

    @Mapping(source = "qualificationCategory.id", target = "qualificationCategoryId")
    ExamDTO toDto(Exam exam);
    List<ExamDTO> toDtoList(List<Exam> exams);
}
