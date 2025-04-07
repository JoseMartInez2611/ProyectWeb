package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.services.FinalNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/final-notes")
public class FinalNoteController {

    @Autowired
    private FinalNoteService finalNoteService;

    // get all final notes
    @GetMapping
    public ResponseEntity<List<FinalNoteDTO>> getAllFinalNotes() {
        return ResponseEntity.ok(finalNoteService.getAll());
    }

    // create final note
    @PostMapping
    public ResponseEntity<FinalNoteDTO> create(FinalNoteDTO dto) {
        return ResponseEntity.ok(finalNoteService.create(dto));
    }

    // get final note by id
    @GetMapping("/{id}")
    public ResponseEntity<FinalNoteDTO> getById(Long id) {
        return ResponseEntity.ok(finalNoteService.getById(id));
    }

    // update final note
    @PutMapping("/{id}")
    public ResponseEntity<FinalNoteDTO> update(Long id, FinalNoteDTO dto) {
        return ResponseEntity.ok(finalNoteService.update(id, dto));
    }

    // delete final note
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        finalNoteService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
