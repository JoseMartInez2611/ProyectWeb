package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.mappers.LessonMapper;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private LessonMapper lessonMapper;

    public List<LessonDTO> getAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessonMapper.toDtoList(lessons);
    }

    public LessonDTO getById(Long id) {
        return lessonMapper.toDto(lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id)));
    }

    public LessonDTO create(Lesson lesson) {
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public LessonDTO update(Long id, Lesson lesson) {
        lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        lesson.setId(id);
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public void delete(Long id) {
        lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        lessonRepository.deleteById(id);
    }
}