package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entity-name")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService entityNameService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable Long id, @RequestBody RoomDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}