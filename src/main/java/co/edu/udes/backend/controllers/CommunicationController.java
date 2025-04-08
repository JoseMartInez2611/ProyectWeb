package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.mappers.CommunicationMapper;
import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.services.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/communication")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

    @GetMapping
    public ResponseEntity<List<CommunicationDTO>> getAll() {
        return ResponseEntity.ok(communicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(communicationService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Communication not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommunicationDTO dto) {
        try{
            Communication communication = CommunicationMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(communicationService.create(communication));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CommunicationDTO dto) {
        try{
            Communication communication = CommunicationMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(communicationService.update(id, dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Communication not found with id: " + id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            communicationService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Communication not found with id: " + id);
        }
    }
}