package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.AcademicRegistration;
import co.edu.udes.backend.repositories.AcademicRegistrationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class AcademicRegistrationController {

    @Autowired
    private AcademicRegistrationRepository academicRegistrationRepository;

    // get all academic registrations
    @GetMapping("/academic-registrations")
    public List<AcademicRegistration> getAllAcademicRegistrations() {
        return academicRegistrationRepository.findAll();
    }

    // create academic registration rest api
    @PostMapping("/academic-registrations")
    public AcademicRegistration createAcademicRegistration(@RequestBody AcademicRegistration academicRegistration) {
        return academicRegistrationRepository.save(academicRegistration);
    }

    // get academic registration by id rest api
    @GetMapping("/academic-registrations/{id}")
    public ResponseEntity<AcademicRegistration> getAcademicRegistrationById(@PathVariable Long id) {
        AcademicRegistration academicRegistration = academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Registration not exist with id :" + id));
        return ResponseEntity.ok(academicRegistration);
    }

    // update academic registration rest api
    @PutMapping("/academic-registrations/{id}")
    public ResponseEntity<AcademicRegistration> updateAcademicRegistration(@PathVariable Long id, @RequestBody AcademicRegistration academicRegistrationDetails) {
        AcademicRegistration academicRegistration = academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Registration not exist with id :" + id));

        academicRegistration.setStudent(academicRegistrationDetails.getStudent());
        academicRegistration.setGroup(academicRegistrationDetails.getGroup());
        academicRegistration.setRegistrationDate(academicRegistrationDetails.getRegistrationDate());

        AcademicRegistration updatedAcademicRegistration = academicRegistrationRepository.save(academicRegistration);
        return ResponseEntity.ok(updatedAcademicRegistration);
    }

    // delete academic registration rest api
    @DeleteMapping("/academic-registrations/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAcademicRegistration(@PathVariable Long id) {
        AcademicRegistration academicRegistration = academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Registration not exist with id :" + id));

        academicRegistrationRepository.delete(academicRegistration);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
