package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import co.edu.udes.backend.mappers.AcademicResourceMapper;
import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.repositories.AcademicResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademicResourceService {

    private final AcademicResourceRepository entityNameRepository;
    private final AcademicResourceMapper entityNameMapper=new AcademicResourceMapper();

    public List<AcademicResourceDTO> getAll() {
        return entityNameRepository.findAll().stream()
                .map(entityNameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AcademicResourceDTO getById(Long id) {
        AcademicResource entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return entityNameMapper.toDTO(entity);
    }

    public AcademicResourceDTO create(AcademicResourceDTO dto) {
        AcademicResource entity = entityNameMapper.toEntity(dto);
        return entityNameMapper.toDTO(entityNameRepository.save(entity));
    }

    public AcademicResourceDTO update(Long id, AcademicResourceDTO dto) {
        entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        AcademicResource updated = entityNameRepository.save(entityNameMapper.toEntity(dto));
        return entityNameMapper.toDTO(updated);
    }

    public void delete(Long id) {
        AcademicResource entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        entityNameRepository.delete(entity);
    }
}