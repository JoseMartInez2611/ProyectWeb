package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.models.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDTO student);

    List<Student> toEntityList(List<StudentDTO> students);

    StudentDTO toDto(Student student);
    List<StudentDTO> toDtoList(List<Student> students);
}
