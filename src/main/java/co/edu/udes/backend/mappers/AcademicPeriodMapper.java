package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicPeriodDTO;
import co.edu.udes.backend.models.AcademicPeriod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicPeriodMapper {
    AcademicPeriodMapper INSTANCE = Mappers.getMapper(AcademicPeriodMapper.class);

    AcademicPeriod toEntity(AcademicPeriodDTO academicPeriod);
    List<AcademicPeriod> toEntityList(List<AcademicPeriodDTO> academicPeriods);

    AcademicPeriodDTO toDto(AcademicPeriod academicPeriod);
    List<AcademicPeriodDTO> toDtoList(List<AcademicPeriod> academicPeriods);
}
