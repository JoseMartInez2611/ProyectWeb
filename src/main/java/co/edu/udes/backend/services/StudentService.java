package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.mappers.StudentMapper;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.repositories.StudentRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDTO> getAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getById(Long id) {
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return studentMapper.toDTO(entity);
    }

    public StudentDTO create(StudentDTO dto) {
        Student entity = studentMapper.toEntity(dto);
        return studentMapper.toDTO(studentRepository.save(entity));
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Student updated = studentRepository.save(studentMapper.toEntity(dto));
        return studentMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        studentRepository.delete(entity);
    }
}
