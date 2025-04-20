package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.mappers.EvaluationMapper;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import co.edu.udes.backend.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final GroupRepository groupRepository;
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

    // modulos

    /**
     * Crea una nueva evaluación y la asocia a un grupo.
     *
     * @param evaluation La entidad Evaluation a crear. Debe contener la información del grupo asociado.
     * @return El EvaluationDTO de la evaluación creada.
     * @throws RuntimeException Si no se encuentra el grupo asociado al ID proporcionado en la evaluación.
     */
    public EvaluationDTO createEvaluation(Evaluation evaluation) {
        // Buscar el grupo asociado por el ID que viene en la entidad Evaluation
        Group group = groupRepository.findById(evaluation.getGroup().getId())
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + evaluation.getGroup().getId()));

        // Establecer la relación entre la evaluación y el grupo encontrado
        evaluation.setGroup(group);

        // Guardar la nueva evaluación en la base de datos
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        // Convertir la entidad guardada a su respectivo DTO y retornarlo
        return evaluationMapper.toDto(savedEvaluation);
    }

    /**
     * Crea múltiples evaluaciones y las asocia a sus respectivos grupos.
     *
     * @param evaluations Una lista de entidades Evaluation a crear. Cada evaluación debe contener la información del grupo asociado.
     * @return Una lista de EvaluationDTO de las evaluaciones creadas.
     */
    public List<EvaluationDTO> createMultipleEvaluations(List<Evaluation> evaluations) {
        List<EvaluationDTO> createdEvaluations = new ArrayList<>();
        // Iterar sobre la lista de evaluaciones proporcionadas
        for (Evaluation evaluation : evaluations) {
            // Para cada evaluación, llamar a la función createEvaluation para guardarla y obtener su DTO
            createdEvaluations.add(createEvaluation(evaluation));
        }
        return createdEvaluations;
    }

}
