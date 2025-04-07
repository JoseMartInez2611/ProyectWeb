package co.edu.udes.backend.controllers;
import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.services.AcademicResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/academic-resource")
@RequiredArgsConstructor
public class AcademicResourceController {

    private final AcademicResourceService entityNameService;

    @GetMapping
    public ResponseEntity<List<AcademicResourceDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicResourceDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AcademicResourceDTO> create(@RequestBody AcademicResourceDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicResourceDTO> update(@PathVariable Long id, @RequestBody AcademicResourceDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}