package co.edu.udes.backend.controllers;


import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<LessonDTO> create(@RequestBody LessonDTO dto){
        return ResponseEntity.ok(lessonService.create(dto));
    }

    // get lesson by id
    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(lessonService.getById(id));
    }

    // update lesson
    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> update(@PathVariable Long id, @RequestBody LessonDTO dto){
        return ResponseEntity.ok(lessonService.update(id, dto));
    }

    // delete lesson
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        lessonService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
