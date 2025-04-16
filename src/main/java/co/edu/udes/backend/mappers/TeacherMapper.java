package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.models.Teacher;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                MessageMapper.class,
                ReportMapper.class,
                BorrowMapper.class,
                CommunicationMapper.class,
                ProfileUMapper.class
        }
)public interface TeacherMapper {

    Teacher toEntity(TeacherDTO teacher);
    List<Teacher> toEntityList(List<TeacherDTO> teachers);

    TeacherDTO toDto(Teacher teacher);
    List<TeacherDTO> toDtoList(List<Teacher> teachers);
}
