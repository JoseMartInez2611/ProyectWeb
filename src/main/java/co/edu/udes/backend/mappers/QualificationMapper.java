package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.models.Qualification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, EvaluationMapper.class})
public interface QualificationMapper {
    QualificationMapper INSTANCE = Mappers.getMapper(QualificationMapper.class);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "evaluationId", target = "evaluation.id")
    Qualification toEntity(QualificationDTO qualification);
    List<Qualification> toEntityList(List<QualificationDTO>  qualifications);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "evaluation.id", target = "evaluationId")
    QualificationDTO toDto(Qualification qualification);
    List<QualificationDTO> toDtoList(List<Qualification> qualifications);
}
