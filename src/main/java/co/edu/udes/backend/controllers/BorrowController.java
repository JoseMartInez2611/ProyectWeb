package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.services.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entity-name")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService entityNameService;

    @GetMapping
    public ResponseEntity<List<BorrowDTO>> getAll() {
        return ResponseEntity.ok(entityNameService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entityNameService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BorrowDTO> create(@RequestBody BorrowDTO dto) {
        return ResponseEntity.ok(entityNameService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowDTO> update(@PathVariable Long id, @RequestBody BorrowDTO dto) {
        return ResponseEntity.ok(entityNameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entityNameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}