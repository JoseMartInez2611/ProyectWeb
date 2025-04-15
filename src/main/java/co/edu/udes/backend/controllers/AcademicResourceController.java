package co.edu.udes.backend.controllers;
import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.mappers.AcademicResourceMapper;
import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.services.AcademicResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/academic-resource")
@RequiredArgsConstructor
public class AcademicResourceController {

    @Autowired
    private final AcademicResourceService academicResourceService;

    @Autowired
    private final AcademicResourceMapper academicResourceMapper;

    @GetMapping
    public ResponseEntity<List<AcademicResourceDTO>> getAll() {
        return ResponseEntity.ok(academicResourceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(academicResourceService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<AcademicResourceDTO> dtos){
        try{
            List<AcademicResource> entities = academicResourceMapper.toEntityList(dtos);
            return ResponseEntity.ok(academicResourceService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AcademicResourceDTO dto) {
        try{
            AcademicResource academicResource = academicResourceMapper.toEntity(dto);
            return ResponseEntity.ok(academicResourceService.update(id, academicResource));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            academicResourceService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}