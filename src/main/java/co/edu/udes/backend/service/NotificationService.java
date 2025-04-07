package co.edu.udes.backend.service;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.mapper.NotificationMapper;
import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.repositories.NotificationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository entityNameRepository;
    private final NotificationMapper entityNameMapper;

    public List<NotificationDTO> getAll() {
        return entityNameRepository.findAll().stream()
                .map(entityNameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getById(Long id) {
        Notification entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return entityNameMapper.toDTO(entity);
    }

    public NotificationDTO create(NotificationDTO dto) {
        Notification entity = entityNameMapper.toEntity(dto);
        return entityNameMapper.toDTO(entityNameRepository.save(entity));
    }

    public NotificationDTO update(Long id, NotificationDTO dto) {
        entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Notification updated = entityNameRepository.save(entityNameMapper.toEntity(dto));
        return entityNameMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Notification entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        entityNameRepository.delete(entity);
    }
}
