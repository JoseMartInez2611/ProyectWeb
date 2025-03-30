package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.repositories.AcademicResourceRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/academic-resources") // Cambiado a /api/v1/academic-resources para mejor semántica
public class AcademicResourceController {

    @Autowired
    private AcademicResourceRepository academicResourceRepository;

    // get all academic resources
    @GetMapping
    public List<AcademicResource> getAllAcademicResources() {
        return academicResourceRepository.findAll();
    }

    // create academic resource rest api
    @PostMapping
    public AcademicResource createAcademicResource(@RequestBody AcademicResource academicResource) {
        return academicResourceRepository.save(academicResource);
    }

    // get academic resource by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<AcademicResource> getAcademicResourceById(@PathVariable Long id) {
        AcademicResource academicResource = academicResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicResource not exist with id :" + id));
        return ResponseEntity.ok(academicResource);
    }

    // update academic resource rest api
    @PutMapping("/{id}")
    public ResponseEntity<AcademicResource> updateAcademicResource(@PathVariable Long id, @RequestBody AcademicResource academicResourceDetails) {
        AcademicResource academicResource = academicResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicResource not exist with id :" + id));

        academicResource.setName(academicResourceDetails.getName());
        academicResource.setCategory(academicResourceDetails.getCategory());
        academicResource.setAvailability(academicResourceDetails.isAvailability());

        AcademicResource updatedAcademicResource = academicResourceRepository.save(academicResource);
        return ResponseEntity.ok(updatedAcademicResource);
    }

    // delete academic resource rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAcademicResource(@PathVariable Long id) {
        AcademicResource academicResource = academicResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicResource not exist with id :" + id));

        academicResourceRepository.delete(academicResource);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
