package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {MessageMapper.class,
                ReportMapper.class,
                BorrowMapper.class,
                CommunicationMapper.class,
                AttendanceMapper.class,
                AcademicRegistrationMapper.class,
                ProfileUMapper.class
        }
)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDTO student);
    List<Student> toEntityList(List<StudentDTO> students);

    StudentDTO toDto(Student student);
    List<StudentDTO> toDtoList(List<Student> students);
}
