package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.models.AbsenceJustification;

import java.util.Collections;
import java.util.List;

public class AbsenceJustificationMapper {

    public AbsenceJustification toEntity(AbsenceJustificationDTO dto) {
        if (dto == null) {
            return null;
        }

        return AbsenceJustification.builder()
                .id(dto.getId())
                .motive(dto.getMotive())
                .description(dto.getDescription())
                .justified(dto.isJustified())
                .build();
    }

    public AbsenceJustificationDTO toDTO(AbsenceJustification entity) {
        if (entity == null) {
            return null;
        }

        return AbsenceJustificationDTO.builder()
                .id(entity.getId())
                .motive(entity.getMotive())
                .description(entity.getDescription())
                .justified(entity.isJustified())
                .build();
    }


    public List<AbsenceJustification> toEntityList(List<AbsenceJustificationDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AbsenceJustificationDTO> toDTOList(List<AbsenceJustification> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
