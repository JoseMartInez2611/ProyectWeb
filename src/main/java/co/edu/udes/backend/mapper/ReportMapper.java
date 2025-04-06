package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.dto.UserDTO;
import co.edu.udes.backend.models.Report;
import co.edu.udes.backend.models.inheritance.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Collectors;

@Component
public class ReportMapper {

    private final UserMapper userMapper;

    public ReportMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
        Report report = new Report();
        report.setId(reportDTO.getId());
        report.setReportType(reportDTO.getReportType());
        report.setContent(reportDTO.getContent());
        report.setGenerationDate(reportDTO.getGenerationDate());
        report.setRequestingUser(userMapper.toEntity(reportDTO.getRequestingUserId()));
        report.setFiles(reportDTO.getFile()); // Be cautious about transferring File objects directly
        return report;
    }
}