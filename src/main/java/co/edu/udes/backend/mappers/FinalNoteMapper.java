package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.models.FinalNote;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface FinalNoteMapper {
    FinalNoteMapper INSTANCE = Mappers.getMapper(FinalNoteMapper.class);

    FinalNote toEntity(FinalNoteDTO finalNote);
    List<FinalNote> toEntityList(List<FinalNoteDTO> finalNotes);

    FinalNoteDTO toDto(FinalNote finalNote);
    List<FinalNoteDTO> toDtoList(List<FinalNote> finalNotes);
}
