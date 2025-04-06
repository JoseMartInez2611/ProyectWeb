package co.edu.udes.backend.mapper;

import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.models.AcademicResource;
import org.springframework.stereotype.Component;

@Component
public class AcademicResourceMapper {

    public AcademicResourceDTO toDTO(AcademicResource academicResource) {
        if (academicResource == null) {
            return null;
        }
        return AcademicResourceDTO.builder()
                .id(academicResource.getId())
                .name(academicResource.getName())
                .category(academicResource.getCategory())
                .availability(academicResource.isAvailability())
                .build();
    }

    public AcademicResource toEntity(AcademicResourceDTO academicResourceDTO) {
        if (academicResourceDTO == null) {
            return null;
        }
        return AcademicResource.builder()
                .id(academicResourceDTO.getId())
                .name(academicResourceDTO.getName())
                .category(academicResourceDTO.getCategory())
                .availability(academicResourceDTO.isAvailability())
                .build();
    }
}