package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherDTO> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return TeacherMapper.INSTANCE.toDtoList(teachers);
    }

    public TeacherDTO getById(Long id) {
        return TeacherMapper.INSTANCE.toDto(teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id)));
    }

    public TeacherDTO create(Teacher teacher) {
        return TeacherMapper.INSTANCE.toDto(teacherRepository.save(teacher));

    }

    public TeacherDTO update(Long id, Teacher teacher) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacher.setId(id);
        return TeacherMapper.INSTANCE.toDto(teacherRepository.save(teacher));
    }

    public void delete(Long id) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacherRepository.deleteById(id);
    }
}
