package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.models.AbsenceJustification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbsenceJustificationMapper {
    AbsenceJustificationMapper INSTANCE = Mappers.getMapper(AbsenceJustificationMapper.class);

    @Mapping(source = "attendanceId", target = "attendance.id")
    AbsenceJustification toEntity(AbsenceJustificationDTO absenceJustification);
    List<AbsenceJustification> toEntityList(List<AbsenceJustificationDTO> absenceJustifications);

    @Mapping(source = "attendance.id", target = "attendanceId")
    AbsenceJustificationDTO toDto(AbsenceJustification absenceJustification);
    List<AbsenceJustificationDTO> toDtoList(List<AbsenceJustification> absenceJustifications);
}
