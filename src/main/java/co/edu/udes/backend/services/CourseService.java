package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.CourseDTO;
import co.edu.udes.backend.mappers.CourseMapper;
import co.edu.udes.backend.models.Career;
import co.edu.udes.backend.models.Course;
import co.edu.udes.backend.repositories.CareerRepository;
import co.edu.udes.backend.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CareerRepository careerRepository;
    @Autowired
    private CourseMapper courseMapper;

    public List<CourseDTO> getAll() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDtoList(courses);
    }

    public CourseDTO getById(Long id) {
        return courseMapper.toDto(courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id)));
    }

    public CourseDTO create(Course course) {
        return courseMapper.toDto(courseRepository.save(course));
    }

    public List<CourseDTO> createMultiple(List<Course> courses) {
        List<CourseDTO> newCourses = new ArrayList<>();
        for (Course course : courses) {
             newCourses.add(create(course));
        }
        return newCourses;
    }

    public CourseDTO update(Long id, Course course) {
        courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        course.setId(id);
        return courseMapper.toDto(courseRepository.save(course));
    }

    public void delete(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        courseRepository.deleteById(id);
    }
}
