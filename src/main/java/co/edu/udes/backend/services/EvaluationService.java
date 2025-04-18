package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.mappers.EvaluationMapper;
import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    @Autowired
    private EvaluationMapper evaluationMapper;

    public List<EvaluationDTO> getAll() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        return evaluationMapper.toDtoList(evaluations);
    }

    public EvaluationDTO getById(Long id) {
        return evaluationMapper.toDto(evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id: " + id)));
    }

    public EvaluationDTO create(Evaluation evaluation) {
        return evaluationMapper.toDto(evaluationRepository.save(evaluation));

    }

    public List<EvaluationDTO> createMultiple(List<Evaluation> users) {
        return evaluationMapper.toDtoList(
                evaluationRepository.saveAll(users)
        );
    }

    public EvaluationDTO update(Long id, Evaluation evaluation) {
        evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id: " + id));
        evaluation.setId(id);
        return evaluationMapper.toDto(evaluationRepository.save(evaluation));
    }

    public void delete(Long id) {
        evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id: " + id));
        evaluationRepository.deleteById(id);
    }
}
