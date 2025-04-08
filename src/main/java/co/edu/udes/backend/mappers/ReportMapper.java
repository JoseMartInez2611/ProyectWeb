package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.models.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportMapper {

    private UserMapper userMapper;

    public ReportDTO toDTO(Report report) {
        if (report == null) {
            return null;
        }
        return ReportDTO.builder()
                .id(report.getId())
                .reportType(report.getReportType())
                .content(report.getContent())
                .generationDate(report.getGenerationDate())
                .requestingUserId(userMapper.toDTO(report.getRequestingProfileU()))
                .build();
    }

    public Report toEntity(ReportDTO reportDTO) {
        if (reportDTO == null) {
            return null;
        }
        return Report.builder()
                .id(reportDTO.getId())
                .reportType(reportDTO.getReportType())
                .content(reportDTO.getContent())
                .generationDate(reportDTO.getGenerationDate())
                .requestingProfileU(userMapper.toEntity(reportDTO.getRequestingUserId()))
                .build();
    }

    public List<ReportDTO> toDTOList(List<Report> reports) {
        if (reports == null) {
            return null;
        }
        return reports.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Report> toEntityList(List<ReportDTO> reportDTOs) {
        if (reportDTOs == null) {
            return null;
        }
        return reportDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}