package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Room;
import co.edu.udes.backend.repositories.RoomRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rooms") // Cambiado a /api/v1/rooms para mejor semántica
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // create room rest api
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // get room by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id :" + id));
        return ResponseEntity.ok(room);
    }

    // update room rest api
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id :" + id));

        room.setCapacity(roomDetails.getCapacity());
        room.setNumber(roomDetails.getNumber());
        room.setFloor(roomDetails.getFloor());
        room.setBuilding(roomDetails.getBuilding());
        room.setResources(roomDetails.getResources());

        Room updatedRoom = roomRepository.save(room);
        return ResponseEntity.ok(updatedRoom);
    }

    // delete room rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id :" + id));

        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}