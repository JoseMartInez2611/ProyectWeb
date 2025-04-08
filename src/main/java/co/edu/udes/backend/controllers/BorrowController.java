package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.mappers.BorrowMapper;
import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.services.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrow")
@RequiredArgsConstructor
public class BorrowController {

    @Autowired
    private final BorrowService borrowService;

    @Autowired
    private final BorrowMapper borrowMapper;

    @GetMapping
    public ResponseEntity<List<BorrowDTO>> getAll() {
        return ResponseEntity.ok(borrowService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(borrowService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BorrowDTO dto) {
        try{
            Borrow borrow = borrowMapper.toEntity(dto);
            return ResponseEntity.ok(borrowService.create(borrow));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BorrowDTO dto) {
        try{
            Borrow borrow = borrowMapper.toEntity(dto);
            return ResponseEntity.ok(borrowService.update(id, borrow));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            borrowService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}