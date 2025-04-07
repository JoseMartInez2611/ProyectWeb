package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.models.Group;

import java.util.Collections;
import java.util.List;

public class GroupMapper {

    private final CourseMapper courseMapper = new CourseMapper();
    private final TeacherMapper teacherMapper = new TeacherMapper();

    public Group toEntity(GroupDTO dto) {
        if (dto == null) {
            return null;
        }

        return Group.builder()
                .id(dto.getId())
                .course(courseMapper.toEntity(dto.getCourse()))
                .teacher(teacherMapper.toEntity(dto.getTeacher()))
                .academicPeriod(dto.getAcademicPeriod())
                .build();
    }

    public GroupDTO toDTO(Group entity) {
        if (entity == null) {
            return null;
        }

        return GroupDTO.builder()
                .id(entity.getId())
                .course(courseMapper.toDTO(entity.getCourse()))
                .teacher(teacherMapper.toDTO(entity.getTeacher()))
                .academicPeriod(entity.getAcademicPeriod())
                .build();
    }

    public List<Group> toEntityList(List<GroupDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<GroupDTO> toDTOList(List<Group> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }


}
