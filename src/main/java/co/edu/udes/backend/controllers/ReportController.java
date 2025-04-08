package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.mappers.ReportMapper;
import co.edu.udes.backend.models.Report;
import co.edu.udes.backend.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    private final ReportService reportService;

    @Autowired
    private final ReportMapper reportMapper;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(reportService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReportDTO dto) {
        try{
            Report report = reportMapper.toEntity(dto);
            return ResponseEntity.ok(reportService.create(report));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ReportDTO dto) {
        try{
            Report report = reportMapper.toEntity(dto);
            return ResponseEntity.ok(reportService.update(id, report));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            reportService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }
}