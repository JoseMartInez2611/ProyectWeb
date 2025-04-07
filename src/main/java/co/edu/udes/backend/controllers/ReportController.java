package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entity-name")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService entityNameService;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReportDTO> create(@RequestBody ReportDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> update(@PathVariable Long id, @RequestBody ReportDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}