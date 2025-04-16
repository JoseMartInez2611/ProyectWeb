package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.models.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {StudentMapper.class,
                LessonMapper.class,
                AbsenceJustificationMapper.class
        }
)
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(source = "lessonId", target = "lesson.id")
    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "justificationId", target = "justification.id")
    Attendance toEntity(AttendanceDTO attendance);
    List<Attendance> toEntityList(List<AttendanceDTO> attendances);

    @Mapping(source = "lesson.id", target = "lessonId")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "justification.id", target = "justificationId")
    AttendanceDTO toDto(Attendance attendance);
    List<AttendanceDTO> toDtoList(List<Attendance> attendances);
}
