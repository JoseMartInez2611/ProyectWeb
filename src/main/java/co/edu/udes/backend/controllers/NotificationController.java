package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService entityNameService;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}