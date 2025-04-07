package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.services.AcademicRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/academic-registrations")
public class AcademicRegistrationController {

    @Autowired
    private AcademicRegistrationService academicRegistrationService;
    // get all academic registrations
    @GetMapping
    public ResponseEntity<List<AcademicRegistrationDTO>> getAll() {
        return ResponseEntity.ok(academicRegistrationService.getAll());
    }

    // create academic registration rest api
    @PostMapping
    public ResponseEntity<AcademicRegistrationDTO> create(@RequestBody AcademicRegistrationDTO dto) {
        return ResponseEntity.ok(academicRegistrationService.create(dto));
    }

    // get academic registration by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<AcademicRegistrationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(academicRegistrationService.getById(id));
    }

    // update academic registration rest api
    @PutMapping("/{id}")
    public ResponseEntity<AcademicRegistrationDTO> update(@PathVariable Long id, @RequestBody AcademicRegistrationDTO dto) {
        return ResponseEntity.ok(academicRegistrationService.update(id, dto));
    }

    // delete academic registration rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        academicRegistrationService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
