package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.mappers.MessageMapper;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @Autowired
    private final MessageMapper messageMapper;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAll() {
        return ResponseEntity.ok(messageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(messageService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<MessageDTO> dtos){
        try{
            List<Message> entities = messageMapper.toEntityList(dtos);
            return ResponseEntity.ok(messageService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MessageDTO dto) {
        try{
            Message message = messageMapper.toEntity(dto);
            return ResponseEntity.ok(messageService.update(id, message));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            messageService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}