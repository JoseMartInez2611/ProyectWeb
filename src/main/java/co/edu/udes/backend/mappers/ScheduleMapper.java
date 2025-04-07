package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.models.Schedule;

import java.util.Collections;
import java.util.List;

public class ScheduleMapper {

    private final DayOfWeekMapper dayOfWeekMapper = new DayOfWeekMapper();

    public ScheduleDTO toDTO(Schedule entity) {
        if (entity == null) {
            return null;
        }

        return ScheduleDTO.builder()
                .id(entity.getId())
                .startHour(entity.getStartHour())
                .endHour(entity.getEndHour())
                .dayOfWeek(dayOfWeekMapper.toDTO(entity.getDayOfWeek()))
                .build();
    }

    public Schedule toEntity(ScheduleDTO dto) {
        if (dto == null) {
            return null;
        }

        return Schedule.builder()
                .id(dto.getId())
                .startHour(dto.getStartHour())
                .endHour(dto.getEndHour())
                .dayOfWeek(dayOfWeekMapper.toEntity(dto.getDayOfWeek()))
                .build();
    }

    public List<ScheduleDTO> toDTOList(List<Schedule> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<Schedule> toEntityList(List<ScheduleDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
