package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.models.inheritance.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EvaluationMapper {
    EvaluationMapper INSTANCE = Mappers.getMapper(EvaluationMapper.class);

    Evaluation toEntity(EvaluationDTO evaluation);
    List<Evaluation> toEntityList(List<EvaluationDTO> evaluations);

    EvaluationDTO toDto(Evaluation evaluation);
    List<EvaluationDTO> toDtoList(List<Evaluation> evaluations);
}
