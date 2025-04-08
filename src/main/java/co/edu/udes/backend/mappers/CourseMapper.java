package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.CourseDTO;
import co.edu.udes.backend.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toEntity(CourseDTO course);
    List<Course> toEntityList(List<CourseDTO> courses);

    CourseDTO toDto(Course course);
    List<CourseDTO> toDtoList(List<Course> courses);
}
