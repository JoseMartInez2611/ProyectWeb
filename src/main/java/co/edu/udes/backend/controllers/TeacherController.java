package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.repositories.TeacherRepository;
import co.edu.udes.backend.services.TeacherService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<TeacherDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(teacherService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(teacherService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
