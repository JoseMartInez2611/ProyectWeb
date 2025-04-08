package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.models.AcademicRegistration;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class AcademicRegistrationMapper {

    private StudentMapper studentMapper;
    private GroupMapper groupMapper;

    public AcademicRegistration toEntity(AcademicRegistrationDTO dto) {
        if (dto == null) {
            return null;
        }

        return AcademicRegistration.builder()
                .id(dto.getId())
                .student(studentMapper.toEntity(dto.getStudent()))
                .group(groupMapper.toEntity(dto.getGroup()))
                .registrationDate(dto.getRegistrationDate())
                .build();
    }

    public AcademicRegistrationDTO toDTO(AcademicRegistration entity) {
        if (entity == null) {
            return null;
        }

        return AcademicRegistrationDTO.builder()
                .id(entity.getId())
                .student(studentMapper.toDTO(entity.getStudent()))
                .group(groupMapper.toDTO(entity.getGroup()))
                .registrationDate(entity.getRegistrationDate())
                .build();
    }

    public List<AcademicRegistration> toEntityList(List<AcademicRegistrationDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AcademicRegistrationDTO> toDTOList(List<AcademicRegistration> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
