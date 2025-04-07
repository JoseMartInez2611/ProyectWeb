package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.CourseDTO;
import co.edu.udes.backend.mappers.CourseMapper;
import co.edu.udes.backend.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> getAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    public CourseDTO getById(Long id) {
        return courseMapper.toDTO(courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id)));
    }

    public CourseDTO create(CourseDTO dto) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(dto)));
    }

    public CourseDTO update(Long id, CourseDTO dto) {
        courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        dto.setId(id);
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        courseRepository.deleteById(id);
    }
}
