package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ScheduleMapper.class,
                GroupMapper.class,
                RoomMapper.class
        }
)
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    @Mapping(source = "scheduleId", target = "schedule.id")
    @Mapping(source = "classroomId", target = "classroom.id")
    @Mapping(source = "groupId", target = "group.id")
    Lesson toEntity(LessonDTO lesson);
    List<Lesson> toEntityList(List<LessonDTO> lessons);

    @Mapping(source = "schedule.id", target = "scheduleId")
    @Mapping(source = "classroom.id", target = "classroomId")
    @Mapping(source = "group.id", target = "groupId")
    LessonDTO toDto(Lesson lesson);
    List<LessonDTO> toDtoList(List<Lesson> lessons);
}
