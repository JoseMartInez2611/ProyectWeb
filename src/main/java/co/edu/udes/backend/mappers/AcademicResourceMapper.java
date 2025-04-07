package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.models.AcademicResource;
import java.util.List;
import java.util.stream.Collectors;


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

    public List<AcademicResourceDTO> toDTOList(List<AcademicResource> academicResources) {
        if (academicResources == null) {
            return null;
        }
        return academicResources.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AcademicResource> toEntityList(List<AcademicResourceDTO> academicResourceDTOs) {
        if (academicResourceDTOs == null) {
            return null;
        }
        return academicResourceDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}