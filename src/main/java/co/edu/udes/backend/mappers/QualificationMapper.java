package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.models.Qualification;

import java.util.Collections;
import java.util.List;

public class QualificationMapper {
    private final StudentMapper studentMapper = new StudentMapper();
    private final EvaluationMapper evaluationMapper = new EvaluationMapper();

    public QualificationDTO toDTO(Qualification qualification) {
        return QualificationDTO.builder()
                .id(qualification.getId())
                .student(studentMapper.toDTO(qualification.getStudent()))
                .qualification(qualification.getQualification())
                .evaluation(evaluationMapper.toDTO(qualification.getEvaluation()))
                .build();
    }

    public Qualification toEntity(QualificationDTO qualificationDTO) {
        return Qualification.builder()
                .id(qualificationDTO.getId())
                .student(studentMapper.toEntity(qualificationDTO.getStudent()))
                .qualification(qualificationDTO.getQualification())
                .evaluation(evaluationMapper.toEntity(qualificationDTO.getEvaluation()))
                .build();
    }

    public List<Qualification> toEntityList(List<QualificationDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<QualificationDTO> toDTOList(List<Qualification> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }


}
