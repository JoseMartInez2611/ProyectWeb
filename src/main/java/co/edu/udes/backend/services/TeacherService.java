package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;

    public List<TeacherDTO> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teacherMapper.toDtoList(teachers);
    }

    public TeacherDTO getById(Long id) {
        return teacherMapper.toDto(teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id)));
    }

    public TeacherDTO create(Teacher teacher) {
        return teacherMapper.toDto(teacherRepository.save(teacher));

    }

    public TeacherDTO update(Long id, Teacher teacher) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacher.setId(id);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    public void delete(Long id) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacherRepository.deleteById(id);
    }
}
