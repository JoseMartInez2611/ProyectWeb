package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicPeriodDTO;
import co.edu.udes.backend.dto.AcademicSubperiodDTO;
import co.edu.udes.backend.models.AcademicPeriod;
import co.edu.udes.backend.models.AcademicSubperiod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicSubperiodMapper {
    AcademicSubperiodMapper INSTANCE = Mappers.getMapper(AcademicSubperiodMapper.class);

    AcademicSubperiod toEntity(AcademicSubperiodDTO academicSubperiod);
    List<AcademicSubperiod> toEntityList(List<AcademicSubperiodDTO> academicSubperiods);

    AcademicSubperiodDTO toDto(AcademicSubperiod academicSubperiod);
    List<AcademicSubperiodDTO> toDtoList(List<AcademicSubperiod> academicSubperiods);
}
