package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.mappers.LessonMapper;
import co.edu.udes.backend.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public List<LessonDTO> getAll() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDTO)
                .toList();
    }

    public LessonDTO getById(Long id) {
        return lessonMapper.toDTO(lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id)));
    }

    public LessonDTO create(LessonDTO dto) {
        return lessonMapper.toDTO(lessonRepository.save(lessonMapper.toEntity(dto)));
    }

    public LessonDTO update(Long id, LessonDTO dto) {
        lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        dto.setId(id);
        return lessonMapper.toDTO(lessonRepository.save(lessonMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        lessonRepository.deleteById(id);
    }
}