package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.models.AcademicRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, GroupMapper.class})
public interface AcademicRegistrationMapper {
    AcademicRegistrationMapper INSTANCE = Mappers.getMapper(AcademicRegistrationMapper.class);


    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "groupId", target = "group.id")
    AcademicRegistration toEntity(AcademicRegistrationDTO academicRegistration);
    List<AcademicRegistration> toEntityList(List<AcademicRegistrationDTO> academicRegistrations);


    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "group.id", target = "groupId")
    AcademicRegistrationDTO toDto(AcademicRegistration academicRegistration);
    List<AcademicRegistrationDTO> toDtoList(List<AcademicRegistration> academicRegistrations);
}
