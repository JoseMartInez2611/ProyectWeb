package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.models.Lesson;

import java.util.Collections;
import java.util.List;

public class LessonMapper {

    private final ScheduleMapper scheduleMapper = new ScheduleMapper();
    private final RoomMapper roomMapper = new RoomMapper();
    private final GroupMapper groupMapper = new GroupMapper();

    public Lesson toEntity(LessonDTO dto) {
        if (dto == null) {
            return null;
        }

        return Lesson.builder()
                .id(dto.getId())
                .schedule(scheduleMapper.toEntity(dto.getSchedule()))
                .classroom(roomMapper.toEntity(dto.getClassroom()))
                .group(groupMapper.toEntity(dto.getGroup()))
                .build();
    }

    public LessonDTO toDTO(Lesson entity) {
        if (entity == null) {
            return null;
        }

        return LessonDTO.builder()
                .id(entity.getId())
                .schedule(scheduleMapper.toDTO(entity.getSchedule()))
                .classroom(roomMapper.toDTO(entity.getClassroom()))
                .group(groupMapper.toDTO(entity.getGroup()))
                .build();
    }

    public List<Lesson> toEntityList(List<LessonDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<LessonDTO> toDTOList(List<Lesson> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
