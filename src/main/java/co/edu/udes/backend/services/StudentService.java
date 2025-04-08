package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.mappers.StudentMapper;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.repositories.StudentRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDtoList(students);
    }

    public StudentDTO getById(Long id) {
        return studentMapper.toDto(studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id)));
    }

    public StudentDTO create(Student student) {
        return studentMapper.toDto(studentRepository.save(student));

    }

    public StudentDTO update(Long id, Student student) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        student.setId(id);
        return studentMapper.toDto(studentRepository.save(student));

    }

    public void delete(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }
}
