package co.edu.udes.backend.service;

import co.edu.udes.backend.dto.ReportDTO;
import co.edu.udes.backend.mapper.ReportMapper;
import co.edu.udes.backend.models.Report;
import co.edu.udes.backend.repositories.ReportRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository entityNameRepository;
    private final ReportMapper entityNameMapper;

    public List<ReportDTO> getAll() {
        return entityNameRepository.findAll().stream()
                .map(entityNameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReportDTO getById(Long id) {
        Report entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return entityNameMapper.toDTO(entity);
    }

    public ReportDTO create(ReportDTO dto) {
        Report entity = entityNameMapper.toEntity(dto);
        return entityNameMapper.toDTO(entityNameRepository.save(entity));
    }

    public ReportDTO update(Long id, ReportDTO dto) {
        entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Report updated = entityNameRepository.save(entityNameMapper.toEntity(dto));
        return entityNameMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Report entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        entityNameRepository.delete(entity);
    }
}
