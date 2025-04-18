package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.models.Student;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")

public interface StudentMapper {

    @Mapping(source = "careerId", target = "career.id")
    Student toEntity(StudentDTO student);
    List<Student> toEntityList(List<StudentDTO> students);

    @Mapping(source = "career.id", target = "careerId")
    StudentDTO toDto(Student student);
    List<StudentDTO> toDtoList(List<Student> students);
}
