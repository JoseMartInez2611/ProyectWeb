package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.AcademicRecord;
import co.edu.udes.backend.repositories.AcademicRecordRepository;
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
public class AcademicRecordController {

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    // get all academic records
    @GetMapping("/academic-records")
    public List<AcademicRecord> getAllAcademicRecords() {
        return academicRecordRepository.findAll();
    }

    // create academic record rest api
    @PostMapping("/academic-records")
    public AcademicRecord createAcademicRecord(@RequestBody AcademicRecord academicRecord) {
        return academicRecordRepository.save(academicRecord);
    }

    // get academic record by id rest api
    @GetMapping("/academic-records/{id}")
    public ResponseEntity<AcademicRecord> getAcademicRecordById(@PathVariable Long id) {
        AcademicRecord academicRecord = academicRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Record not exist with id :" + id));
        return ResponseEntity.ok(academicRecord);
    }

    // update academic record rest api
    @PutMapping("/academic-records/{id}")
    public ResponseEntity<AcademicRecord> updateAcademicRecord(@PathVariable Long id, @RequestBody AcademicRecord academicRecordDetails) {
        AcademicRecord academicRecord = academicRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Record not exist with id :" + id));

        academicRecord.setStudent(academicRecordDetails.getStudent());
        academicRecord.setAcademicAverage(academicRecordDetails.getAcademicAverage());
        academicRecord.setNotes(academicRecordDetails.getNotes());

        AcademicRecord updatedAcademicRecord = academicRecordRepository.save(academicRecord);
        return ResponseEntity.ok(updatedAcademicRecord);
    }

    // delete academic record rest api
    @DeleteMapping("/academic-records/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAcademicRecord(@PathVariable Long id) {
        AcademicRecord academicRecord = academicRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic Record not exist with id :" + id));

        academicRecordRepository.delete(academicRecord);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
