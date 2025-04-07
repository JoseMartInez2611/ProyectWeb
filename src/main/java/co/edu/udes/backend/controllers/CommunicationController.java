package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.services.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/communication")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService entityNameService;

    @GetMapping
    public ResponseEntity<List<CommunicationDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunicationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CommunicationDTO> create(@RequestBody CommunicationDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunicationDTO> update(@PathVariable Long id, @RequestBody CommunicationDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}