package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.models.AcademicResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicResourceMapper {
    AcademicResourceMapper INSTANCE = Mappers.getMapper(AcademicResourceMapper.class);

    AcademicResource toEntity(AcademicResourceDTO academicResource);
    List<AcademicResource> toEntityList(List<AcademicResourceDTO> academicResources);

    AcademicResourceDTO toDto(AcademicResource academicResource);
    List<AcademicResourceDTO> toDtoList(List<AcademicResource> academicResources);
}