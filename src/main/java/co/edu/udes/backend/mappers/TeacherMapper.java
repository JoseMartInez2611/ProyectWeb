package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher toEntity(TeacherDTO teacher);
    List<Teacher> toEntityList(List<TeacherDTO> teachers);

    TeacherDTO toDto(Teacher teacher);
    List<TeacherDTO> toDtoList(List<Teacher> teachers);
}
