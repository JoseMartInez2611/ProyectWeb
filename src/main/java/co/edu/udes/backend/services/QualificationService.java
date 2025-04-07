package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.mappers.QualificationMapper;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.repositories.QualificationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;
    private final QualificationMapper qualificationMapper;

    public List<QualificationDTO> getAll() {
        return qualificationRepository.findAll().stream()
                .map(qualificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QualificationDTO getById(Long id) {
        Qualification entity = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return qualificationMapper.toDTO(entity);
    }

    public QualificationDTO create(QualificationDTO dto) {
        Qualification entity = qualificationMapper.toEntity(dto);
        return qualificationMapper.toDTO(qualificationRepository.save(entity));
    }

    public QualificationDTO update(Long id, QualificationDTO dto) {
        qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Qualification updated = qualificationRepository.save(qualificationMapper.toEntity(dto));
        return qualificationMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Qualification entity = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        qualificationRepository.delete(entity);
    }
}
