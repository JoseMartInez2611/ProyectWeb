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

    public List<ReportDTO> getAll() {
        List<Report> reports =   reportRepository.findAll();
        return ReportMapper.INSTANCE.toDtoList(reports);
    }

    public ReportDTO getById(Long id) {
        return ReportMapper.INSTANCE.toDto(reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id)));
    }

    public ReportDTO create(Report report) {
        return ReportMapper.INSTANCE.toDto(reportRepository.save(report));

    }

    public ReportDTO update(Long id, Report report) {
        reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        report.setId(id);
        return ReportMapper.INSTANCE.toDto(reportRepository.save(report));
    }

    public void delete(Long id) {
        reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        reportRepository.deleteById(id);
    }
}
