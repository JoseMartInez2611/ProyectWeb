package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.models.Question;
import co.edu.udes.backend.repositories.QuestionRepository;
import co.edu.udes.backend.services.QuestionService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO dto) {
        return ResponseEntity.ok(questionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> update(@PathVariable Long id, @RequestBody QuestionDTO dto) {
        return ResponseEntity.ok(questionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
