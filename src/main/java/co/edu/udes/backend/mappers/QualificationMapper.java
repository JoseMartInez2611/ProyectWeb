package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.models.Qualification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, EvaluationMapper.class})
public interface QualificationMapper {
    QualificationMapper INSTANCE = Mappers.getMapper(QualificationMapper.class);

    Qualification toEntity(QualificationDTO qualification);
    List<Qualification> toEntityList(List<QualificationDTO>  qualifications);

    QualificationDTO toDto(Qualification qualification);
    List<QualificationDTO> toDtoList(List<Qualification> qualifications);
}
