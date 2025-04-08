package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.mappers.ReportMapper;
import co.edu.udes.backend.models.Report;
import co.edu.udes.backend.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private ReportMapper reportMapper;

    public List<ReportDTO> getAll() {
        List<Report> reports =   reportRepository.findAll();
        return reportMapper.toDtoList(reports);
    }

    public ReportDTO getById(Long id) {
        return reportMapper.toDto(reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id)));
    }

    public ReportDTO create(Report report) {
        return reportMapper.toDto(reportRepository.save(report));

    }

    public ReportDTO update(Long id, Report report) {
        reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        report.setId(id);
        return reportMapper.toDto(reportRepository.save(report));
    }

    public void delete(Long id) {
        reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        reportRepository.deleteById(id);
    }
}
