package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Report;
import co.edu.udes.backend.repositories.ReportRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports") // Cambiado a /api/v1/reports para mejor semántica
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    // get all reports
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // create report rest api
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportRepository.save(report);
    }

    // get report by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not exist with id :" + id));
        return ResponseEntity.ok(report);
    }

    // update report rest api
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not exist with id :" + id));

        report.setReportType(reportDetails.getReportType());
        report.setContent(reportDetails.getContent());
        report.setGenerationDate(reportDetails.getGenerationDate());
        report.setRequestingUser(reportDetails.getRequestingUser());
        report.setFiles(reportDetails.getFiles());

        Report updatedReport = reportRepository.save(report);
        return ResponseEntity.ok(updatedReport);
    }

    // delete report rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteReport(@PathVariable Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not exist with id :" + id));

        reportRepository.delete(report);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}