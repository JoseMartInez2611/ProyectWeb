package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, TeacherMapper.class})
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "teacherId", target = "teacher.id")
    @Mapping(source = "academicPeriodId", target = "academicPeriod.id")
    Group toEntity(GroupDTO group);
    List<Group> toEntityList(List<GroupDTO> groups);

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "academicPeriod.id", target = "academicPeriodId")
    GroupDTO toDto(Group group);
    List<GroupDTO> toDtoList(List<Group> groups);
}
