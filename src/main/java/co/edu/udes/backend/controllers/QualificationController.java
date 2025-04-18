package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.mappers.QualificationMapper;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.services.QualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/qualification")
@RequiredArgsConstructor
public class QualificationController {

    @Autowired
    private final QualificationService qualificationService;

    @Autowired
    private final QualificationMapper qualificationMapper;

    @GetMapping
    public ResponseEntity<List<QualificationDTO>> getAll() {
        return ResponseEntity.ok(qualificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(qualificationService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("average/{id}")
    public ResponseEntity<?> getAverageById(@PathVariable Long id) {
        System.out.println("Controller GertAverageById");
        try{
            return ResponseEntity.ok().body(qualificationService.getAverage(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<QualificationDTO> dtos){
        try{
            System.out.println("Controller dato que llega"+dtos);
            List<Qualification> entities = qualificationMapper.toEntityList(dtos);
            System.out.println("Controller "+qualificationService.createMultiple(entities));
            return ResponseEntity.ok(qualificationService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody QualificationDTO dto) {
        try{
            Qualification qualification = qualificationMapper.toEntity(dto);
            return ResponseEntity.ok(qualificationService.update(id, qualification));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            qualificationService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
