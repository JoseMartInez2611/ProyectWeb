package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.models.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileUMapper.class})
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    Report toEntity(ReportDTO report);
    List<Report> toEntityList(List<ReportDTO> reports);

    ReportDTO toDto(Report report);
    List<ReportDTO> toDtoList(List<Report> reports);
}