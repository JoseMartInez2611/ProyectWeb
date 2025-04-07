package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.services.AcademicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/academic-records")
public class AcademicRecordController {

    @Autowired
    private AcademicRecordService academicRecordService;
    // get all academic records
    @GetMapping
    public ResponseEntity<List<AcademicRecordDTO>> getAll() {
        return ResponseEntity.ok(academicRecordService.getAll());
    }

    // create academic record rest api
    @PutMapping
    public ResponseEntity<AcademicRecordDTO> create(@RequestBody AcademicRecordDTO dto) {
        return ResponseEntity.ok(academicRecordService.create(dto));
    }

    // get academic record by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<AcademicRecordDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(academicRecordService.getById(id));
    }

    // update academic record rest api
    @PutMapping("/{id}")
    public ResponseEntity<AcademicRecordDTO> update(@PathVariable Long id, @RequestBody AcademicRecordDTO dto) {
        return ResponseEntity.ok(academicRecordService.update(id, dto));
    }

    // delete academic record rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        academicRecordService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
