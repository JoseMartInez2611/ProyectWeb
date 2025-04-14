package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {GroupMapper.class,
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
