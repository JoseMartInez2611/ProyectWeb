package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(teacherService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeacherDTO dto) {
        try{
            Teacher teacher = TeacherMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(teacherService.create(teacher));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        try{
            Teacher teacher = TeacherMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(teacherService.update(id, dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found with id: " + id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            teacherService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found with id: " + id);
        }

    }
}
