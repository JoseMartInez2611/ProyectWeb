package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.models.Report;

import java.util.List;
import java.util.stream.Collectors;


public class ReportMapper {

    private final UserMapper userMapper = new UserMapper();

    public ReportDTO toDTO(Report report) {
        if (report == null) {
            return null;
        }
        return ReportDTO.builder()
                .id(report.getId())
                .reportType(report.getReportType())
                .content(report.getContent())
                .generationDate(report.getGenerationDate())
                .requestingUserId(userMapper.toDTO(report.getRequestingUser()))
                .file(report.getFiles()) // Be cautious about transferring File objects directly
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
                .requestingUser(userMapper.toEntity(reportDTO.getRequestingUserId()))
                .files(reportDTO.getFile())
                .build();// Be cautious about transferring File objects directly
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