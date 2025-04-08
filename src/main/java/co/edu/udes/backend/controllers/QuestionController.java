package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.mappers.QuestionMapper;
import co.edu.udes.backend.models.Question;
import co.edu.udes.backend.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(questionService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QuestionDTO dto) {
        try{
            Question question = QuestionMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(questionService.create(question));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody QuestionDTO dto) {
        try{
            Question question = QuestionMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(questionService.update(id, dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found with id: " + id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            questionService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found with id: " + id);
        }

    }

}
