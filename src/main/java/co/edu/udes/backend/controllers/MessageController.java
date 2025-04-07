package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entity-name")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService entityNameService;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MessageDTO> create(@RequestBody MessageDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable Long id, @RequestBody MessageDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}