package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AnswerDocumentDTO;
import co.edu.udes.backend.mappers.AnswerDocumentMapper;
import co.edu.udes.backend.models.AnswerDocument;
import co.edu.udes.backend.services.AnswerDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer-document")
@RequiredArgsConstructor
public class AnswerDocumentController {

    @Autowired
    private final AnswerDocumentService answerDocumentService;

    @Autowired
    private final AnswerDocumentMapper answerDocumentMapper;

    @GetMapping
    public ResponseEntity<List<AnswerDocumentDTO>> getAll() {
        return ResponseEntity.ok(answerDocumentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(answerDocumentService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnswerDocumentDTO dto) {
        try{
            AnswerDocument answerDocument = answerDocumentMapper.toEntity(dto);
            return ResponseEntity.ok(answerDocumentService.create(answerDocument));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnswerDocumentDTO dto) {
        try{
            AnswerDocument answerDocument = answerDocumentMapper.toEntity(dto);
            return ResponseEntity.ok(answerDocumentService.update(id, answerDocument));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            answerDocumentService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
