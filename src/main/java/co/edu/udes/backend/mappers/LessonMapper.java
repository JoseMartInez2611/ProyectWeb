package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    Lesson toEntity(LessonDTO lesson);
    List<Lesson> toEntityList(List<LessonDTO> lessons);

    LessonDTO toDto(Lesson lesson);
    List<LessonDTO> toDtoList(List<Lesson> lessons);
}
