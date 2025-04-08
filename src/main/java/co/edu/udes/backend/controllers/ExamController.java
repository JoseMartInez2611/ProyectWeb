package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.mappers.ExamMapper;
import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(examService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExamDTO dto) {
        try{
            Exam exam = ExamMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(examService.create(exam));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExamDTO dto) {
        try{
            Exam exam = ExamMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(examService.update(id, exam));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            examService.getById(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

