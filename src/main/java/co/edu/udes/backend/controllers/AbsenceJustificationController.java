package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.mappers.AbsenceJustificationMapper;
import co.edu.udes.backend.models.AbsenceJustification;
import co.edu.udes.backend.services.AbsenceJustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/absence-justifications")
@PreAuthorize("permitAll()")
public class AbsenceJustificationController {

    @Autowired
    private AbsenceJustificationService absenceJustificationService;

    @Autowired
    private AbsenceJustificationMapper absenceJustificationMapper;

    // Get all absence justifications
    @GetMapping
    public ResponseEntity<List<AbsenceJustificationDTO>> getAllAbsenceJustifications() {
        return ResponseEntity.ok(absenceJustificationService.getAll());
    }

    // Create absence justification
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<AbsenceJustificationDTO> dtos){
        try{
            List<AbsenceJustification> entities = absenceJustificationMapper.toEntityList(dtos);
            return ResponseEntity.ok(absenceJustificationService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    // Get absence justification by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(absenceJustificationService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Update absence justification
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AbsenceJustificationDTO dto) {
        try{
            AbsenceJustification absenceJustification = absenceJustificationMapper.toEntity(dto);
            return ResponseEntity.ok(absenceJustificationService.update(id, absenceJustification));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }

    }

    // Delete absence justification
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            absenceJustificationService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
