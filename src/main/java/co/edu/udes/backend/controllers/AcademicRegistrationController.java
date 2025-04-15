package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.mappers.AcademicRegistrationMapper;
import co.edu.udes.backend.models.AcademicRegistration;
import co.edu.udes.backend.services.AcademicRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/academic-registrations")
public class AcademicRegistrationController {

    @Autowired
    private AcademicRegistrationService academicRegistrationService;

    @Autowired
    private AcademicRegistrationMapper  academicRegistrationMapper;

    // get all academic registrations
    @GetMapping
    public ResponseEntity<List<AcademicRegistrationDTO>> getAll() {
        return ResponseEntity.ok(academicRegistrationService.getAll());
    }

    // create academic registration rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<AcademicRegistrationDTO> dtos){
        try{
            List<AcademicRegistration> entities = academicRegistrationMapper.toEntityList(dtos);
            return ResponseEntity.ok(academicRegistrationService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    // get academic registration by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(academicRegistrationService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update academic registration rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AcademicRegistrationDTO dto) {
        try{
            AcademicRegistration academicRecord = academicRegistrationMapper.toEntity(dto);
            return ResponseEntity.ok(academicRegistrationService.update(id, academicRecord));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete academic registration rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            academicRegistrationService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
