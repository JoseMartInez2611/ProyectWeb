package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.models.FinalNote;

import java.util.Collections;
import java.util.List;

public class FinalNoteMapper {

    public FinalNote toEntity(FinalNoteDTO dto) {
        if (dto == null) {
            return null;
        }

        return FinalNote.builder()
                .id(dto.getId())
                .note(dto.getNote())
                .build();
    }

    public FinalNoteDTO toDTO(FinalNote entity) {
        if (entity == null) {
            return null;
        }

        return FinalNoteDTO.builder()
                .id(entity.getId())
                .note(entity.getNote())
                .build();
    }

    public List<FinalNote> toEntityList(List<FinalNoteDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<FinalNoteDTO> toDTOList(List<FinalNote> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
