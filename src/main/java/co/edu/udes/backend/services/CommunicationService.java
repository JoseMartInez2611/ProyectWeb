package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.mappers.CommunicationMapper;
import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.repositories.CommunicationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final CommunicationRepository entityNameRepository;
    private final CommunicationMapper entityNameMapper;

    public List<CommunicationDTO> getAll() {
        return entityNameRepository.findAll().stream()
                .map(entityNameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommunicationDTO getById(Long id) {
        Communication entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return entityNameMapper.toDTO(entity);
    }

    public CommunicationDTO create(CommunicationDTO dto) {
        Communication entity = entityNameMapper.toEntity(dto);
        return entityNameMapper.toDTO(entityNameRepository.save(entity));
    }

    public CommunicationDTO update(Long id, CommunicationDTO dto) {
        entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Communication updated = entityNameRepository.save(entityNameMapper.toEntity(dto));
        return entityNameMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Communication entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        entityNameRepository.delete(entity);
    }
}
