package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    Schedule toEntity(ScheduleDTO schedule);
    List<Schedule> toEntityList(List<ScheduleDTO> schedules);

    ScheduleDTO toDto(Schedule schedule);
    List<ScheduleDTO> toDtoList(List<Schedule> schedules);
}
