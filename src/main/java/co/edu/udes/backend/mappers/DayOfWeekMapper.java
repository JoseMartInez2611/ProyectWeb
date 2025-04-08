package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.DayOfWeekDTO;
import co.edu.udes.backend.models.DayOfWeek;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DayOfWeekMapper {
    DayOfWeekMapper INSTANCE = Mappers.getMapper(DayOfWeekMapper.class);

    DayOfWeek toEntity(DayOfWeekDTO dayOfWeek);
    List<DayOfWeek> toEntityList(List<DayOfWeekDTO> dayOfWeeks);

    DayOfWeekDTO toDto(DayOfWeek dayOfWeek);
    List<DayOfWeekDTO> toDtoList(List<DayOfWeek> dayOfWeeks);
}
