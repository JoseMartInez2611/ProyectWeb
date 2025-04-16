package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DayOfWeekMapper.class})
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "dayId", target = "dayOfWeek.id")
    Schedule toEntity(ScheduleDTO schedule);
    List<Schedule> toEntityList(List<ScheduleDTO> schedules);

    @Mapping(source = "dayOfWeek.id", target = "dayId")
    ScheduleDTO toDto(Schedule schedule);
    List<ScheduleDTO> toDtoList(List<Schedule> schedules);
}
