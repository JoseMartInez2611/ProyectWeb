package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.AbsenceJustification;
import co.edu.udes.backend.repositories.AbsenceJustificationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class AbsenceJustificationController {

    @Autowired
    private AbsenceJustificationRepository absenceJustificationRepository;

    // Get all absence justifications
    @GetMapping("/absence-justifications")
    public List<AbsenceJustification> getAllAbsenceJustifications() {
        return absenceJustificationRepository.findAll();
    }

    // Create absence justification
    @PostMapping("/absence-justifications")
    public AbsenceJustification createAbsenceJustification(@RequestBody AbsenceJustification absenceJustification) {
        return absenceJustificationRepository.save(absenceJustification);
    }

    // Get absence justification by id
    @GetMapping("/absence-justifications/{id}")
    public ResponseEntity<AbsenceJustification> getAbsenceJustificationById(@PathVariable Long id) {
        AbsenceJustification absenceJustification = absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absence Justification not exist with id :" + id));
        return ResponseEntity.ok(absenceJustification);
    }

    // Update absence justification
    @PutMapping("/absence-justifications/{id}")
    public ResponseEntity<AbsenceJustification> updateAbsenceJustification(@PathVariable Long id, @RequestBody AbsenceJustification absenceJustificationDetails) {
        AbsenceJustification absenceJustification = absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absence Justification not exist with id :" + id));

        absenceJustification.setMotive(absenceJustificationDetails.getMotive());
        absenceJustification.setDescription(absenceJustificationDetails.getDescription());
        absenceJustification.setJustified(absenceJustificationDetails.isJustified());

        AbsenceJustification updatedAbsenceJustification = absenceJustificationRepository.save(absenceJustification);
        return ResponseEntity.ok(updatedAbsenceJustification);
    }

    // Delete absence justification
    @DeleteMapping("/absence-justifications/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAbsenceJustification (@PathVariable Long id) {
        AbsenceJustification absenceJustification = absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Absence Justification not exist with id :" + id));

        absenceJustificationRepository.delete(absenceJustification);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
