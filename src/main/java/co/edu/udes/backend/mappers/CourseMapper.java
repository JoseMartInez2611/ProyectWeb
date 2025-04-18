package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.CourseDTO;
import co.edu.udes.backend.models.Career;
import co.edu.udes.backend.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "prerequisiteIds", target = "prerequisites", qualifiedByName = "mapIdsToCourses")
    @Mapping(source = "careerIds", target = "careers", qualifiedByName = "mapIdsToCareers")
    Course toEntity(CourseDTO dto);

    @Mapping(source = "prerequisites", target = "prerequisiteIds", qualifiedByName = "mapCoursesToIds")
    @Mapping(source = "careers", target = "careerIds", qualifiedByName = "mapCareersToIds")
    CourseDTO toDto(Course course);

    List<Course> toEntityList(List<CourseDTO> dtoList);
    List<CourseDTO> toDtoList(List<Course> entityList);

    @Named("mapCoursesToIds")
    static List<Long> mapCoursesToIds(List<Course> courses) {
        if (courses == null) return null;
        return courses.stream()
                .map(Course::getId)
                .toList();
    }

    @Named("mapCareersToIds")
    static List<Long> mapCareersToIds(List<Career> careers) {
        if (careers == null) return null;
        return careers.stream()
                .map(Career::getId)
                .toList();
    }

    @Named("mapIdsToCourses")
    static List<Course> mapIdsToCourses(List<Long> ids) {
        if (ids == null) return null;
        List<Course> courses = new ArrayList<>();
        for  ( Long id : ids ) {
            Course course = new Course();
            course.setId(id);
            courses.add(course);
        }
        return courses;
    }

    @Named("mapIdsToCareers")
    static List<Career> mapIdsToCareers(List<Long> ids) {
        if (ids == null) return null;
        List<Career> careers = new ArrayList<>();
        for  ( Long id : ids ) {
            Career career = new Career();
            career.setId(id);
            careers.add(career);
        }
        return careers;
    }
}
