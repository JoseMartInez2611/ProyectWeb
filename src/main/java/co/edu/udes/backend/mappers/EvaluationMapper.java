package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.models.inheritance.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface EvaluationMapper {
    EvaluationMapper INSTANCE = Mappers.getMapper(EvaluationMapper.class);

    @Mapping(source = "qualificationCategoryId", target = "qualificationCategory.id")
    Evaluation toEntity(EvaluationDTO evaluation);
    List<Evaluation> toEntityList(List<EvaluationDTO> evaluations);

    @Mapping(source = "qualificationCategory.id", target = "qualificationCategoryId")
    EvaluationDTO toDto(Evaluation evaluation);
    List<EvaluationDTO> toDtoList(List<Evaluation> evaluations);
}
