package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.mappers.CommunicationMapper;
import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.services.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/communication")
@RequiredArgsConstructor
public class CommunicationController {

    @Autowired
    private final CommunicationService communicationService;

    @Autowired
    private final CommunicationMapper communicationMapper;


    @GetMapping
    public ResponseEntity<List<CommunicationDTO>> getAll() {
        return ResponseEntity.ok(communicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(communicationService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<CommunicationDTO> dtos){
        try{
            List<Communication> entities = communicationMapper.toEntityList(dtos);
            return ResponseEntity.ok(communicationService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CommunicationDTO dto) {
        try{
            Communication communication = communicationMapper.toEntity(dto);
            return ResponseEntity.ok(communicationService.update(id, communication));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}