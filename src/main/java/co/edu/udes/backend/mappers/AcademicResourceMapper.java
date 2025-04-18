package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.models.AcademicResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicResourceMapper {
    AcademicResourceMapper INSTANCE = Mappers.getMapper(AcademicResourceMapper.class);

    @Mapping(source = "roomId", target = "room.id")
    AcademicResource toEntity(AcademicResourceDTO academicResource);
    List<AcademicResource> toEntityList(List<AcademicResourceDTO> academicResources);

    @Mapping(source = "room.id", target = "roomId")
    AcademicResourceDTO toDto(AcademicResource academicResource);
    List<AcademicResourceDTO> toDtoList(List<AcademicResource> academicResources);
}