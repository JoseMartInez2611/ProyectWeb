package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.models.AcademicRecord;

import java.util.Collections;
import java.util.List;

public class AcademicRecordMapper {

    private final StudentMapper studentMapper = new StudentMapper();
    private final FinalNoteMapper finalNoteMapper = new FinalNoteMapper();

    public AcademicRecord toEntity(AcademicRecordDTO dto) {
        if (dto == null) {
            return null;
        }

        return AcademicRecord.builder()
                .id(dto.getId())
                .academicAverage(dto.getAcademicAverage())
                .student(studentMapper.toEntity(dto.getStudent()))
                .finalNotes(finalNoteMapper.toEntityList(dto.getFinalNotes()))
                .build();
    }

    public AcademicRecordDTO toDTO(AcademicRecord entity) {
        if (entity == null) {
            return null;
        }

        return AcademicRecordDTO.builder()
                .id(entity.getId())
                .academicAverage(entity.getAcademicAverage())
                .student(studentMapper.toDTO(entity.getStudent()))
                .finalNotes(finalNoteMapper.toDTOList(entity.getFinalNotes()))
                .build();
    }

    public List<AcademicRecord> toEntityList(List<AcademicRecordDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AcademicRecordDTO> toDTOList(List<AcademicRecord> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

}
