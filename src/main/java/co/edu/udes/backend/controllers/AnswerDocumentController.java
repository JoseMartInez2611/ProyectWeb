package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AnswerDocumentDTO;
import co.edu.udes.backend.services.AnswerDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer-document")
@RequiredArgsConstructor
public class AnswerDocumentController {

    @Autowired
    private final AnswerDocumentService answerDocumentService;

    @GetMapping
    public ResponseEntity<List<AnswerDocumentDTO>> getAll() {
        return ResponseEntity.ok(answerDocumentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDocumentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(answerDocumentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AnswerDocumentDTO> create(@RequestBody AnswerDocumentDTO dto) {
        return ResponseEntity.ok(answerDocumentService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerDocumentDTO> update(@PathVariable Long id, @RequestBody AnswerDocumentDTO dto) {
        return ResponseEntity.ok(answerDocumentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        answerDocumentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
