package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicSubperiodDTO;
import co.edu.udes.backend.mappers.AcademicSubperiodMapper;
import co.edu.udes.backend.models.AcademicSubperiod;
import co.edu.udes.backend.services.AcademicSubperiodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/academic-subperiods")
public class AcademicSubperiodController {
    @Autowired
    private AcademicSubperiodService academicSubperiodService;

    @Autowired
    private AcademicSubperiodMapper academicSubperiodMapper;

    // get all academic subperiods
    @GetMapping
    public ResponseEntity<List<AcademicSubperiodDTO>> getAllAcademicSubperiods() {
        return ResponseEntity.ok(academicSubperiodService.getAll());
    }

    // create academic subperiod rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<AcademicSubperiodDTO> dtos){
        try{
            List<AcademicSubperiod> entities = academicSubperiodMapper.toEntityList(dtos);
            return ResponseEntity.ok(academicSubperiodService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    // get academic subperiod by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(academicSubperiodService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update academic subperiod rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AcademicSubperiodDTO dto) {
        try{
            AcademicSubperiod academicSubperiod = academicSubperiodMapper.toEntity(dto);
            return ResponseEntity.ok(academicSubperiodService.update(id, academicSubperiod));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete academic subperiod rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            academicSubperiodService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
