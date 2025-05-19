package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.mappers.FinalNoteMapper;
import co.edu.udes.backend.models.FinalNote;
import co.edu.udes.backend.services.FinalNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/final-notes")
public class FinalNoteController {

    @Autowired
    private FinalNoteService finalNoteService;

    @Autowired
    private FinalNoteMapper finalNoteMapper;

    // get all final notes
    @GetMapping
    public ResponseEntity<List<FinalNoteDTO>> getAllFinalNotes() {
        return ResponseEntity.ok(finalNoteService.getAll());
    }


    //Get the Course final note by stundent and group id
    @GetMapping("/definitive/{idStudent}/{idGroup}")
    public ResponseEntity<?> getCourseFinalNote(@PathVariable Long idStudent, @PathVariable Long idGroup) {
        try{
            return ResponseEntity.ok().body(finalNoteService.getLessonFinalNote(idStudent, idGroup));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // create final note
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<FinalNoteDTO> dtos){
        try{
            List<FinalNote> entities = finalNoteMapper.toEntityList(dtos);
            return ResponseEntity.ok(finalNoteService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }


    // get final note by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(finalNoteService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update final note
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FinalNoteDTO dto) {
        try{
            FinalNote finalNote = finalNoteMapper.toEntity(dto);
            return ResponseEntity.ok(finalNoteService.update(id, finalNote));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete final note
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            finalNoteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
