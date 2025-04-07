package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.mappers.EvaluationMapper;
import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper evaluationMapper;

    public List<EvaluationDTO> getAll() {
        return evaluationRepository.findAll().stream()
                .map(evaluationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EvaluationDTO getById(Long id) {
        Evaluation entity = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return evaluationMapper.toDTO(entity);
    }

    public EvaluationDTO create(EvaluationDTO dto) {
        Evaluation entity = evaluationMapper.toEntity(dto);
        return evaluationMapper.toDTO(evaluationRepository.save(entity));
    }

    public EvaluationDTO update(Long id, EvaluationDTO dto) {
        evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Evaluation updated = evaluationRepository.save(evaluationMapper.toEntity(dto));
        return evaluationMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Evaluation entity = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        evaluationRepository.delete(entity);
    }
}
