package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.repositories.ExamRepository;
import co.edu.udes.backend.services.ExamService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    @Autowired
    private final ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAll() {
        return ResponseEntity.ok(examService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(examService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ExamDTO> create(@RequestBody ExamDTO dto) {
        return ResponseEntity.ok(examService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@PathVariable Long id, @RequestBody ExamDTO dto) {
        return ResponseEntity.ok(examService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        examService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

