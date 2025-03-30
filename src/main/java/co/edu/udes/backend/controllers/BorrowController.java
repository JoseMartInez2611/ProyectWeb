package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.repositories.BorrowRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/borrows") // Cambiado a /api/v1/borrows para mejor semántica
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    // get all borrows
    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    // create borrow rest api
    @PostMapping
    public Borrow createBorrow(@RequestBody Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    // get borrow by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not exist with id :" + id));
        return ResponseEntity.ok(borrow);
    }

    // update borrow rest api
    @PutMapping("/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Long id, @RequestBody Borrow borrowDetails) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not exist with id :" + id));

        borrow.setBorrowDate(borrowDetails.getBorrowDate());
        borrow.setReturnDate(borrowDetails.getReturnDate());
        borrow.setDuration(borrowDetails.getDuration());
        borrow.setResource(borrowDetails.getResource());
        borrow.setLender(borrowDetails.getLender());

        Borrow updatedBorrow = borrowRepository.save(borrow);
        return ResponseEntity.ok(updatedBorrow);
    }

    // delete borrow rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBorrow(@PathVariable Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not exist with id :" + id));

        borrowRepository.delete(borrow);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}