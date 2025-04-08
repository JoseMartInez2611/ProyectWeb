package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.mappers.RoomMapper;
import co.edu.udes.backend.models.Room;
import co.edu.udes.backend.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomDTO> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return RoomMapper.INSTANCE.toDtoList(rooms);
    }

    public RoomDTO getById(Long id) {
        return RoomMapper.INSTANCE.toDto(roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id)));
    }

    public RoomDTO create(Room room) {
        return RoomMapper.INSTANCE.toDto(roomRepository.save(room));

    }

    public RoomDTO update(Long id, Room room) {
        roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        room.setId(id);
        return RoomMapper.INSTANCE.toDto(roomRepository.save(room));
    }

    public void delete(Long id) {
        roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        roomRepository.deleteById(id);
    }
}
