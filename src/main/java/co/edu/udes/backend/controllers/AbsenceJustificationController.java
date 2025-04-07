package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.services.AbsenceJustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/absence-justifications")
public class AbsenceJustificationController {

    @Autowired
    private AbsenceJustificationService absenceJustificationService;

    // Get all absence justifications
    @GetMapping
    public ResponseEntity<List<AbsenceJustificationDTO>> getAllAbsenceJustifications() {
        return ResponseEntity.ok(absenceJustificationService.getAll());
    }

    // Create absence justification
    @PostMapping
    public ResponseEntity<AbsenceJustificationDTO> create(@RequestBody AbsenceJustificationDTO dto){
        return ResponseEntity.ok(absenceJustificationService.create(dto));
    }

    // Get absence justification by id
    @GetMapping("/{id}")
    public ResponseEntity<AbsenceJustificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(absenceJustificationService.getById(id));
    }
    // Update absence justification
    @PutMapping("/{id}")
    public ResponseEntity<AbsenceJustificationDTO> update(@PathVariable Long id, @RequestBody AbsenceJustificationDTO dto) {
        return ResponseEntity.ok(absenceJustificationService.update(id, dto));
    }

    // Delete absence justification
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        absenceJustificationService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
