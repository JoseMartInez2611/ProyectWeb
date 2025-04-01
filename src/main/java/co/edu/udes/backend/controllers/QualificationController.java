package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.repositories.QualificationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class QualificationController {

    @Autowired
    private QualificationRepository qualificationRepository;

    // get all qualifications
    @GetMapping("/qualifications")
    public List<Qualification> getAllQualifications(@RequestBody Qualification qualification){
        return qualificationRepository.findAll();
    }

    //create qualification
    @PostMapping("/qualifications")
    public Qualification createQualification(@RequestBody Qualification qualification){
        return qualificationRepository.save(qualification);
    }

    //get qualification by id
    @GetMapping("/qualifications/{id}")
    public ResponseEntity<Qualification> getQualificationById(@PathVariable Long id){
        Qualification qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not exist with id :" + id));
        return ResponseEntity.ok(qualification);
    }

    //update qualification
    @PutMapping("/qualifications/{id}")
    public ResponseEntity<Qualification> updateQualification(@PathVariable Long id, @RequestBody Qualification qualification){
        Qualification existingQualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not exist with id :" + id));

        existingQualification.setQualification(qualification.getQualification());
        existingQualification.setEvaluation(qualification.getEvaluation());
        existingQualification.setStudent(qualification.getStudent());

        Qualification updatedQualification = qualificationRepository.save(existingQualification);
        return ResponseEntity.ok(updatedQualification);
    }

    // delete qualification
    @DeleteMapping("/qualifications/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteQualification(@PathVariable Long id){
        Qualification qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not exist with id :" + id));
        qualificationRepository.delete(qualification);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
