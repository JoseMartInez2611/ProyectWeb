package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicPeriodDTO;
import co.edu.udes.backend.mappers.AcademicPeriodMapper;
import co.edu.udes.backend.models.AcademicPeriod;
import co.edu.udes.backend.services.AcademicPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/academic-periods")
public class AcademicPeriodController {
    @Autowired
    private AcademicPeriodService academicPeriodService;

    @Autowired
    private AcademicPeriodMapper academicPeriodMapper;

    // get all academic periods
    @GetMapping
    public ResponseEntity<List<AcademicPeriodDTO>> getAllAcademicPeriods() {
        return ResponseEntity.ok(academicPeriodService.getAll());
    }

    // create the actual academic period rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AcademicPeriodDTO academicPeriodDTO) {
        try{
            AcademicPeriod academicPeriod = academicPeriodMapper.toEntity(academicPeriodDTO);
            return ResponseEntity.ok(academicPeriodService.create(academicPeriod));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error creating academic period: " + e.getMessage());
        }
    }

    // get academic period by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(academicPeriodService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
