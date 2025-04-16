package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.models.FinalNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinalNoteMapper {
    FinalNoteMapper INSTANCE = Mappers.getMapper(FinalNoteMapper.class);

    @Mapping(source = "academicRecordId", target = "academicRecord.id")
    @Mapping(source = "groupId", target = "group.id")
    FinalNote toEntity(FinalNoteDTO finalNote);
    List<FinalNote> toEntityList(List<FinalNoteDTO> finalNotes);

    @Mapping(source = "academicRecord.id", target = "academicRecordId")
    @Mapping(source = "group.id", target = "groupId")
    FinalNoteDTO toDto(FinalNote finalNote);
    List<FinalNoteDTO> toDtoList(List<FinalNote> finalNotes);
}
