package co.edu.udes.backend.controllers;


import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.mappers.LessonMapper;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    // get all lessons
    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAll(){
        return ResponseEntity.ok(lessonService.getAll());
    }

    // create lesson
    @PostMapping
    public ResponseEntity<?> create(@RequestBody LessonDTO dto){
        try{
            Lesson lesson = LessonMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(lessonService.create(lesson));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // get lesson by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(lessonService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found with id: " + id);
        }
    }

    // update lesson
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LessonDTO dto){
        try{
            Lesson lesson = LessonMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(lessonService.update(id, dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found with id: " + id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete lesson
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            lessonService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found with id: " + id);
        }
    }
}
