package co.edu.udes.backend.service;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.mapper.RoomMapper;
import co.edu.udes.backend.models.Room;
import co.edu.udes.backend.repositories.RoomRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository entityNameRepository;
    private final RoomMapper entityNameMapper;

    public List<RoomDTO> getAll() {
        return entityNameRepository.findAll().stream()
                .map(entityNameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RoomDTO getById(Long id) {
        Room entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return entityNameMapper.toDTO(entity);
    }

    public RoomDTO create(RoomDTO dto) {
        Room entity = entityNameMapper.toEntity(dto);
        return entityNameMapper.toDTO(entityNameRepository.save(entity));
    }

    public RoomDTO update(Long id, RoomDTO dto) {
        entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Room updated = entityNameRepository.save(entityNameMapper.toEntity(dto));
        return entityNameMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Room entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        entityNameRepository.delete(entity);
    }
}
