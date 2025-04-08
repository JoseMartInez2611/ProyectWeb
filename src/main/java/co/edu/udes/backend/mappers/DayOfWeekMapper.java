package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.DayOfWeekDTO;
import co.edu.udes.backend.models.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DayOfWeekMapper {

    public DayOfWeek toEntity(DayOfWeekDTO dto) {
        if (dto == null) {
            return null;
        }

        return DayOfWeek.builder()
                .id(dto.getId())
                .day(dto.getDay())
                .build();
    }

    public DayOfWeekDTO toDTO(DayOfWeek entity) {
        if (entity == null) {
            return null;
        }

        return DayOfWeekDTO.builder()
                .id(entity.getId())
                .day(entity.getDay())
                .build();
    }

    public List<DayOfWeek> toEntityList(List<DayOfWeekDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<DayOfWeekDTO> toDTOList(List<DayOfWeek> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
