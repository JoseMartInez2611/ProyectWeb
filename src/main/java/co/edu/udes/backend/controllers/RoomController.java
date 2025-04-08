package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.mappers.RoomMapper;
import co.edu.udes.backend.models.Room;
import co.edu.udes.backend.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(roomService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoomDTO dto) {
        try{
            Room room = RoomMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(roomService.create(room));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RoomDTO dto) {
        try{
            Room room = RoomMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(roomService.update(id, dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with id: " + id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            roomService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with id: " + id);
        }
    }
}