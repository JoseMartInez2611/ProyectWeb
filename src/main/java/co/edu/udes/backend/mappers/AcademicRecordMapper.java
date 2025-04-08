package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.models.AcademicRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AcademicRecordMapper {
    AcademicRecordMapper INSTANCE = Mappers.getMapper(AcademicRecordMapper.class);
    AcademicRecord toEntity(AcademicRecordDTO academicRecord);
    List<AcademicRecord> toEntityList(List<AcademicRecordDTO> academicRecords);

    AcademicRecordDTO toDto(AcademicRecord academicRecord);
    List<AcademicRecordDTO> toDtoList(List<AcademicRecord> academicRecords);
}
