package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.repositories.QualificationRepository;
import co.edu.udes.backend.services.QualificationService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/qualification")
@RequiredArgsConstructor
public class QualificationController {

    @Autowired
    private final QualificationService qualificationService;

    @GetMapping
    public ResponseEntity<List<QualificationDTO>> getAll() {
        return ResponseEntity.ok(qualificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QualificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(qualificationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<QualificationDTO> create(@RequestBody QualificationDTO dto) {
        return ResponseEntity.ok(qualificationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QualificationDTO> update(@PathVariable Long id, @RequestBody QualificationDTO dto) {
        return ResponseEntity.ok(qualificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        qualificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
