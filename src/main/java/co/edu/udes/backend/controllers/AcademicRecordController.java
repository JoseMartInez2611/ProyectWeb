package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.mappers.AcademicRecordMapper;
import co.edu.udes.backend.models.AcademicRecord;
import co.edu.udes.backend.services.AcademicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AcademicRecordDTO dto) {
        try{
            AcademicRecord academicRecord = AcademicRecordMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(academicRecordService.create(academicRecord));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // get academic record by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(academicRecordService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update academic record rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AcademicRecordDTO dto) {
        try{
            AcademicRecord academicRecord = AcademicRecordMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(academicRecordService.update(id, academicRecord));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete academic record rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            academicRecordService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
