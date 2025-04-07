package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.models.inheritance.Evaluation;

import java.util.Collections;
import java.util.List;

public class EvaluationMapper {

    public EvaluationDTO toDTO(Evaluation evalution) {

        return EvaluationDTO.builder()
                .id(evalution.getId())
                .evaluationRubric(evalution.getEvaluationRubric())
                .date(evalution.getDate())
                .group(GroupMapper.toDTO(evalution.getGroup()))
                .build();
    }

    public Evaluation toEntity(EvaluationDTO evaluationDTO) {

        return Evaluation.builder()
                .id(evaluationDTO.getId())
                .evaluationRubric(evaluationDTO.getEvaluationRubric())
                .date(evaluationDTO.getDate())
                .group(GroupMapper.toEntity(evaluationDTO.getGroup()))
                .build();
    }

    public List<Evaluation> toEntityList(List<EvaluationDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<EvaluationDTO> toDTOList(List<Evaluation> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

}
